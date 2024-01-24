package com.campfood.src.store.mapper;

import com.campfood.src.member.entity.Member;
import com.campfood.src.store.dto.StoreInquiryAllDTO;
import com.campfood.src.store.dto.StoreInquiryDetailDTO;
import com.campfood.src.store.dto.StoreInquiryPopularDTO;
import com.campfood.src.store.dto.StoreSearchByKeywordDTO;
import com.campfood.src.store.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreMapper {
    public Store toStore() {
        return Store.builder().build();
    }

    public StoreHeart toStoreHeart(Member member, Store store) {
        return StoreHeart.builder()
                .store(store)
                .member(member)
                .isChecked(false)
                .build();
    }

    public StoreInquiryAllDTO toInquiryByTagDTO(Store store) {

        return StoreInquiryAllDTO.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeCategories(toTags(store.getStoreCategories()))
                .storeImage(store.getImage())
                .naverRate(store.getNaverRate())
                .naverVisitedReviewCnt(store.getNaverVisitedReviewCnt())
                .naverBlogReviewCnt(store.getNaverBlogReviewCnt())
                .campFoodRate(store.getCampFoodRate())
                .camFoodReviewCnt(store.getCampFoodReviewCnt())
                .build();
    }

    public StoreInquiryDetailDTO toInquiryDetailDTO(Store store) {

        List<StoreInquiryDetailDTO.OpenTimeInfo> openTimeInfos = store.getStoreOpenTimes().stream()
                .map(this::toOpenTimeInfo)
                .collect(Collectors.toList());

        return StoreInquiryDetailDTO.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeCategories(toTags(store.getStoreCategories()))
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

    public StoreSearchByKeywordDTO toSearchByKeywordDTO(Store store) {
        return StoreSearchByKeywordDTO.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeCategories(toTags(store.getStoreCategories()))
                .storeImage(store.getImage())
                .campFoodRate(store.getCampFoodRate())
                .campFoodReviewCnt(store.getCampFoodReviewCnt())
                .build();
    }

    public StoreInquiryPopularDTO toInquiryByPopularDTO(Store store) {
        return StoreInquiryPopularDTO.builder()
                .storeId(store.getId())
                .storeImage(store.getImage())
                .storeCategory(toTags(store.getStoreCategories()).get(0))
                .build();
    }

    private List<Category> toTags(List<StoreCategory> storeCategories) {
        return storeCategories.stream()
                .map(StoreCategory::getCategory)
                .collect(Collectors.toList());
    }

    private StoreInquiryDetailDTO.OpenTimeInfo toOpenTimeInfo(StoreOpenTime storeOpenTime) {
        return StoreInquiryDetailDTO.OpenTimeInfo.builder()
                .day(storeOpenTime.getDay())
                .openTime(storeOpenTime.getOpenTime())
                .breakTime(storeOpenTime.getBreakTime())
                .lastOrder(storeOpenTime.getLastOrder())
                .build();
    }
}
