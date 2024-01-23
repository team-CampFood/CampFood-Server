package com.campfood.src.store.service;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.RestApiException;
import com.campfood.src.member.entity.Member;
import com.campfood.src.store.dto.*;
import com.campfood.src.store.entity.Store;
import com.campfood.src.store.entity.StoreHeart;
import com.campfood.src.store.entity.Tag;
import com.campfood.src.store.mapper.StoreMapper;
import com.campfood.src.store.repository.StoreHeartRepository;
import com.campfood.src.store.repository.StoreRepository;
import com.campfood.src.store.repository.StoreTagRepository;
import com.campfood.src.university.entity.University;
import com.campfood.src.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreHeartRepository storeHeartRepository;
    private final StoreTagRepository storeTagRepository;

    private final UniversityService universityService;

    private final StoreMapper storeMapper;

    @Transactional
    public boolean toggleStoreHeart(Long storeId) {
        // 로그인 유저 -> 받아오는 로직 필요
        Member member = Member.builder().build();

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RestApiException(ErrorCode.STORE_NOT_EXIST));

        StoreHeart storeHeart = storeHeartRepository.findByMemberAndStore(member, store)
                .orElseGet(() -> {
                    StoreHeart newStoreHeart = storeMapper.toStoreHeart(member, store);
                    return storeHeartRepository.save(newStoreHeart);
                });

        storeHeart.toggleStoreHeart();

        return storeHeart.isChecked();
    }

    public PageResponse<StoreInquiryAllDTO> inquiryStoresByTag(Tag tag, Pageable pageable) {

        Page<Store> stores = storeTagRepository.findAllByTag(tag, pageable);

        return new PageResponse<>(
                stores.map(storeMapper::toInquiryByTagDTO).stream().toList(),
                stores.hasNext()
        );
    }

    public PageResponse<StoreInquiryAllDTO> inquiryStoresByUniversity(String name, Pageable pageable) {
        University university = universityService.findUniversityByName(name);

        Page<Store> stores = storeRepository.findAllByUniversity(university, pageable);

        return new PageResponse<>(
                stores.map(storeMapper::toInquiryByTagDTO).stream().toList(),
                stores.hasNext()
        );
    }

    public StoreInquiryDetailDTO inquiryStoreDetail(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RestApiException(ErrorCode.STORE_NOT_EXIST));

        return storeMapper.toInquiryDetailDTO(store);
    }

    public PageResponse<StoreSearchByKeywordDTO> searchStoresByKeyword(final String keyword, Pageable pageable) {
        Page<Store> stores = storeRepository.findByKeyword(keyword, pageable);

        return new PageResponse<>(
                stores.map(storeMapper::toSearchByKeywordDTO).stream().toList(),
                stores.hasNext()
        );
    }

    public List<StoreInquiryPopularDTO> inquiryStoresByPopular(String universityName) {
        // 대학교 명을 안 보냈을 경우 전체에서 조회
        if (universityName == null) {
            List<Store> stores = storeRepository.findTop10ByOrderByCampFoodRateDesc();
            return stores.stream()
                    .map(storeMapper::toInquiryByPopularDTO)
                    .toList();
        }

        University university = universityService.findUniversityByName(universityName);

        List<Store> stores = storeRepository.findTop10ByUniversityAndOrderByCampFoodRateDesc(university, PageRequest.of(0, 10));

        return stores.stream()
                .map(storeMapper::toInquiryByPopularDTO)
                .toList();
    }
}
