package com.campfood.src.store.mapper;

import com.campfood.src.member.entity.Member;
import com.campfood.src.store.dto.request.StoreUpdateDTO;
import com.campfood.src.store.dto.response.*;
import com.campfood.src.store.entity.*;
import com.campfood.src.university.entity.University;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreMapper {
    public Store toStore(StoreUpdateDTO request) {
        return Store.builder()
                .identificationId(request.getIdentificationId())
                .name(request.getName())
                .naverRate(request.getRate())
                .naverVisitedReviewCnt(request.getVisitedReview())
                .naverBlogReviewCnt(request.getBlogReview())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();

    }

    public StoreUniversity toStoreUniversity(University university, Store store) {
        return StoreUniversity.builder()
                .store(store)
                .university(university)
                .build();
    }

    public StoreHeart toStoreHeart(Member member, Store store) {
        return StoreHeart.builder()
                .store(store)
                .member(member)
                .isChecked(false)
                .build();
    }

    public StoreInquiryAllDTO toInquiryByTagDTO(Store store, List<StoreCategory> storeCategories) {

        return StoreInquiryAllDTO.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeCategories(toTags(storeCategories))
                .storeImage(store.getImage())
                .naverRate(store.getNaverRate())
                .naverVisitedReviewCnt(store.getNaverVisitedReviewCnt())
                .naverBlogReviewCnt(store.getNaverBlogReviewCnt())
                .campFoodRate(store.getCampFoodRate())
                .camFoodReviewCnt(store.getCampFoodReviewCnt())
                .build();
    }

    public StoreInquiryDetailDTO toInquiryDetailDTO(Store store, List<StoreCategory> storeCategories, List<StoreOpenTime> storeOpenTimes) {

        List<StoreInquiryDetailDTO.OpenTimeInfo> openTimeInfos = storeOpenTimes.stream()
                .map(this::toOpenTimeInfo)
                .collect(Collectors.toList());

        return StoreInquiryDetailDTO.builder()
                .storeId(store.getId())
                .identificationId(store.getIdentificationId())
                .storeName(store.getName())
                .storeCategories(toTags(storeCategories))
                .storeImage(store.getImage())
                .naverRate(store.getNaverRate())
                .naverVisitedReviewCnt(store.getNaverVisitedReviewCnt())
                .naverBlogReviewCnt(store.getNaverBlogReviewCnt())
                .campFoodRate(store.getCampFoodRate())
                .camFoodReviewCnt(store.getCampFoodReviewCnt())
                .storeAddress(store.getAddress())
                .openTimeInfos(openTimeInfos)
                .storeNumber(store.getStoreNumber())
                .build();
    }

    public StoreSearchByKeywordDTO toSearchByKeywordDTO(Store store, List<StoreCategory> storeCategories) {
        return StoreSearchByKeywordDTO.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeCategories(toTags(storeCategories))
                .storeImage(store.getImage())
                .campFoodRate(store.getCampFoodRate())
                .campFoodReviewCnt(store.getCampFoodReviewCnt())
                .build();
    }

    public StoreInquiryByPopularDTO toInquiryByPopularDTO(Store store, List<StoreCategory> storeCategories) {
        return StoreInquiryByPopularDTO.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeImage(store.getImage())
                .storeCategory(toTags(storeCategories).get(0))
                .build();
    }

    public StoreInquiryByHeartDTO toInquiryByHeartDTO(Store store) {
        return StoreInquiryByHeartDTO.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeImage(store.getImage())
                .naverRate(store.getNaverRate())
                .naverVisitedReviewCnt(store.getNaverVisitedReviewCnt())
                .naverBlogReviewCnt(store.getNaverBlogReviewCnt())
                .campFoodRate(store.getCampFoodRate())
                .campFoodReviewCnt(store.getCampFoodReviewCnt())
                .build();
    }

    private List<String> toTags(List<StoreCategory> storeCategories) {
        return storeCategories.stream()
                .map(StoreCategory::getCategory)
                .map(Category::getToKorean)
                .collect(Collectors.toList());
    }

    private StoreInquiryDetailDTO.OpenTimeInfo toOpenTimeInfo(StoreOpenTime storeOpenTime) {
        return StoreInquiryDetailDTO.OpenTimeInfo.builder()
                .day(storeOpenTime.getDay())
                .content(storeOpenTime.getContent())
                .build();
    }
}
