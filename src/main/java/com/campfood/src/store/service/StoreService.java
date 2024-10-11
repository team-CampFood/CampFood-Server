package com.campfood.src.store.service;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.RestApiException;
import com.campfood.common.service.EntityLoader;
import com.campfood.src.member.Auth.AuthUtils;
import com.campfood.src.member.entity.Member;
import com.campfood.src.store.dto.request.StoreUpdateDTO;
import com.campfood.src.store.dto.response.*;
import com.campfood.src.store.entity.*;
import com.campfood.src.store.mapper.StoreMapper;
import com.campfood.src.store.repository.*;
import com.campfood.src.university.entity.University;
import com.campfood.src.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService implements EntityLoader<Store, Long> {

    private final StoreRepository storeRepository;
    private final StoreHeartRepository storeHeartRepository;
    private final StoreCategoryRepository storeCategoryRepository;
    private final StoreOpenTimeRepository storeOpenTimeRepository;
    private final StoreUniversityRepository storeUniversityRepository;

    private final UniversityService universityService;

    private final StoreMapper storeMapper;
    private final AuthUtils authUtils;
    private KDNode root;

    @PostConstruct
    public void initializeKDTree() {
        List<Store> stores = storeRepository.findAll();
        root = buildKDTree(stores, 0);
    }

    private KDNode buildKDTree(List<Store> stores, int depth) {
        if (stores.isEmpty()) {
            return null;
        }

        int axis = depth % 2;
        stores.sort((a, b) -> axis == 0 ?
                Double.compare(Double.parseDouble(a.getLatitude()), Double.parseDouble(b.getLatitude())) :
                Double.compare(Double.parseDouble(a.getLongitude()), Double.parseDouble(b.getLongitude())));

        int medianIndex = stores.size() / 2;
        KDNode node = new KDNode(stores.get(medianIndex));
        node.left = buildKDTree(stores.subList(0, medianIndex), depth + 1);
        node.right = buildKDTree(stores.subList(medianIndex + 1, stores.size()), depth + 1);

        return node;
    }

    @Transactional
    public Long updateStore(StoreUpdateDTO request) {
        Store store = storeRepository.findByIdentificationId(request.getIdentificationId())
                .orElseGet(() -> storeRepository.save(storeMapper.toStore(request)));

        store.updateStore(request);

        // 가게 대학 정보 확인
        University university = getUniversityByName(request.getUniversityName(), store);

        return store.getId();
    }

    @Transactional
    public boolean toggleStoreHeart(Long storeId) {
        Member loginMember = authUtils.getMemberByAuthentication();

        Store store = loadEntity(storeId);

        StoreHeart storeHeart = storeHeartRepository.findByMemberAndStore(loginMember, store)
                .orElseGet(() -> {
                    StoreHeart newStoreHeart = storeMapper.toStoreHeart(loginMember, store);
                    return storeHeartRepository.save(newStoreHeart);
                });

        storeHeart.toggleStoreHeart();

        return storeHeart.isChecked();
    }

    public StorePageResponse<StoreInquiryAllDTO> inquiryStoresByTag(Category category, Pageable pageable) {

        Page<Store> stores = storeCategoryRepository.findAllByTag(category, pageable);

        return new StorePageResponse<>(
                stores.map(store -> storeMapper.toInquiryByTagDTO(store, storeCategoryRepository.findAllByStore(store)))
                        .stream().collect(Collectors.toList()),
                stores.hasNext()
        );
    }

    public StorePageResponse<StoreInquiryAllDTO> inquiryStoresByUniversity(String name, Pageable pageable) {
        University university = universityService.findUniversityByName(name);

        Page<Store> stores = storeUniversityRepository.findAllByUniversity(university, pageable)
                .map(StoreUniversity::getStore);

        return new StorePageResponse<>(
                stores.map(store -> storeMapper.toInquiryByTagDTO(store, storeCategoryRepository.findAllByStore(store)))
                        .stream().collect(Collectors.toList()),
                stores.hasNext()
        );
    }

    public StoreInquiryDetailDTO inquiryStoreDetail(Long storeId) {
        Store store = loadEntity(storeId);

        return storeMapper.toInquiryDetailDTO(store,
                storeCategoryRepository.findAllByStore(store),
                storeOpenTimeRepository.findAllByStore(store));
    }

    public StorePageResponse<StoreSearchByKeywordDTO> searchStoresByKeyword(final String keyword, Pageable pageable) {
        Page<Store> stores = storeRepository.findByKeyword(keyword, pageable);

        return new StorePageResponse<>(
                stores.map(store -> storeMapper.toSearchByKeywordDTO(store, storeCategoryRepository.findAllByStore(store)))
                        .stream().collect(Collectors.toList()),
                stores.hasNext()
        );
    }

    public List<StoreInquiryByPopularDTO> inquiryStoresByPopular(String universityName) {
        // 대학교 명을 안 보냈을 경우 전체에서 조회
        if (universityName == null) {
            List<Store> stores = storeRepository.findTop10ByOrderByCampFoodRateDesc();
            return stores.stream()
                    .map(store -> storeMapper.toInquiryByPopularDTO(store, storeCategoryRepository.findAllByStore(store)))
                    .collect(Collectors.toList());
        }

        University university = universityService.findUniversityByName(universityName);

        List<Store> stores = storeUniversityRepository.findTop10ByUniversity(university, PageRequest.of(0, 10));

        return stores.stream()
                .map(store -> storeMapper.toInquiryByPopularDTO(store, storeCategoryRepository.findAllByStore(store)))
                .collect(Collectors.toList());
    }

    public StorePageResponse<StoreInquiryByHeartDTO> inquiryStoresByHeart(Pageable pageable) {
        Member loginMember = authUtils.getMemberByAuthentication();

        Page<Store> stores = storeHeartRepository.findAllByMemberAndIsCheckedIsTrue(loginMember, pageable)
                .map(StoreHeart::getStore);

        return new StorePageResponse<>(
                stores.map(storeMapper::toInquiryByHeartDTO).stream().collect(Collectors.toList()),
                stores.hasNext()
        );
    }


    private University getUniversityByName(String universityName, Store store) {
        return storeUniversityRepository.findAllByStore(store).stream()
                .map(StoreUniversity::getUniversity)
                .filter(university -> university.getName().equals(universityName))
                .findAny()
                .orElse(null);
    }

    @Override
    public Store loadEntity(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new RestApiException(ErrorCode.STORE_NOT_EXIST));
    }

    @Override
    public StoreNearestDTO inquiryNearestStores(Double latitude, Double longitude) {
        if (root == null) {
            return null;
        }

        Store nearestStore = findNearest(root, latitude, longitude, 0);

        if (nearestStore == null) {
            return null;
        }

        return StoreNearestDTO.builder()
                .id(nearestStore.getId())
                .name(nearestStore.getName())
                .latitude(Double.parseDouble(nearestStore.getLatitude()))
                .longitude(Double.parseDouble(nearestStore.getLongitude()))
                .build();
    }

    private Store findNearest(KDNode node, double latitude, double longitude, int depth) {
        if (node == null) {
            return null;
        }

        int axis = depth % 2;
        double nodeValue = (axis == 0) ? Double.parseDouble(node.store.getLatitude()) : Double.parseDouble(node.store.getLongitude());
        double targetValue = (axis == 0) ? latitude : longitude;

        KDNode nextBranch = (targetValue < nodeValue) ? node.left : node.right;
        KDNode otherBranch = (targetValue < nodeValue) ? node.right : node.left;

        Store candidate = findNearest(nextBranch, latitude, longitude, depth + 1);
        Store best = cmp(node.store, candidate, latitude, longitude) <= 0 ? node.store : candidate;

        if (otherBranch != null) {
            if (Math.abs(targetValue - nodeValue) < distance(latitude, longitude, Double.parseDouble(best.getLatitude()), Double.parseDouble(best.getLongitude()))) {
                Store otherBest = findNearest(otherBranch, latitude, longitude, depth + 1);
                best = cmp(best, otherBest, latitude, longitude) <= 0 ? best : otherBest;
            }
        }

        return best;
    }

    private int cmp(Store s1, Store s2, double latitude, double longitude) {
        if (s1 == null && s2 == null) return 0;
        if (s1 == null) return 1;
        if (s2 == null) return -1;

        double d1 = distance(latitude, longitude, Double.parseDouble(s1.getLatitude()), Double.parseDouble(s1.getLongitude()));
        double d2 = distance(latitude, longitude, Double.parseDouble(s2.getLatitude()), Double.parseDouble(s2.getLongitude()));
        return Double.compare(d1, d2);
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double dx = lat1 - lat2;
        double dy = lon1 - lon2;
        return Math.sqrt(dx * dx + dy * dy);
    }


    public class KDNode {
        Store store;
        KDNode left, right;

        public KDNode(Store store) {
            this.store = store;
            this.left = this.right = null;
        }
    }

}
