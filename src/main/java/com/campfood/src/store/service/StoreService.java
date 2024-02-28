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

    @Transactional
    public Long updateStore(StoreUpdateDTO request) {
        Store store = storeRepository.findByIdentificationId(request.getIdentificationId())
                .orElseGet(() -> storeRepository.save(storeMapper.toStore(request)));

        store.updateStore(request);
        store.updateCategories(toStoreCategories(request.getCategories(), store));
        store.updateOpenTimes(toStoreOpenTimes(request.getOpeningTimes(), store));

        // 가게 대학 정보 확인
        University university = getUniversityByName(request.getUniversityName(), store);
        // 해당 대학이 정보가 없다면 추가
        if (university == null) {
            store.addUniversity(storeMapper.toStoreUniversity(
                    universityService.findUniversityByName(request.getUniversityName()), store)
            );
        }

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
                stores.map(storeMapper::toInquiryByTagDTO).stream().collect(Collectors.toList()),
                stores.hasNext()
        );
    }

    public StorePageResponse<StoreInquiryAllDTO> inquiryStoresByUniversity(String name, Pageable pageable) {
        University university = universityService.findUniversityByName(name);

        Page<Store> stores = storeUniversityRepository.findAllByUniversity(university, pageable)
                .map(StoreUniversity::getStore);

        return new StorePageResponse<>(
                stores.map(storeMapper::toInquiryByTagDTO).stream().collect(Collectors.toList()),
                stores.hasNext()
        );
    }

    public StoreInquiryDetailDTO inquiryStoreDetail(Long storeId) {
        Store store = loadEntity(storeId);

        return storeMapper.toInquiryDetailDTO(store);
    }

    public StorePageResponse<StoreSearchByKeywordDTO> searchStoresByKeyword(final String keyword, Pageable pageable) {
        Page<Store> stores = storeRepository.findByKeyword(keyword, pageable);

        return new StorePageResponse<>(
                stores.map(storeMapper::toSearchByKeywordDTO).stream().collect(Collectors.toList()),
                stores.hasNext()
        );
    }

    public List<StoreInquiryByPopularDTO> inquiryStoresByPopular(String universityName) {
        // 대학교 명을 안 보냈을 경우 전체에서 조회
        if (universityName == null) {
            List<Store> stores = storeRepository.findTop10ByOrderByCampFoodRateDesc();
            return stores.stream()
                    .map(storeMapper::toInquiryByPopularDTO)
                    .collect(Collectors.toList());
        }

        University university = universityService.findUniversityByName(universityName);

        List<Store> stores = storeUniversityRepository.findTop10ByUniversity(university, PageRequest.of(0, 10));

        return stores.stream()
                .map(storeMapper::toInquiryByPopularDTO)
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

    private List<StoreCategory> toStoreCategories(List<Category> categories, Store store) {

        // 기존 카테고리 삭제
        List<StoreCategory> oldCategories = store.getStoreCategories();
        storeCategoryRepository.deleteAll(oldCategories);

        return categories.stream()
                .map(category -> storeMapper.toStoreCategory(category, store))
                .map(storeCategoryRepository::save)
                .collect(Collectors.toList());
    }

    private List<StoreOpenTime> toStoreOpenTimes(List<StoreUpdateDTO.OpeningTime> openingTimes, Store store) {
        // 기존 오픈 시간 삭제
        List<StoreOpenTime> oldOpenTimes = store.getStoreOpenTimes();
        storeOpenTimeRepository.deleteAll(oldOpenTimes);

        return openingTimes.stream()
                .map(openingTime -> storeMapper.toStoreOpenTime(openingTime, store))
                .map(storeOpenTimeRepository::save)
                .collect(Collectors.toList());
    }

    private University getUniversityByName(String universityName, Store store) {
        return store.getUniversities().stream()
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
}
