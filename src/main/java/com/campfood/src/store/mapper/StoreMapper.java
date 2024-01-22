package com.campfood.src.store.mapper;

import com.campfood.src.member.entity.Member;
import com.campfood.src.store.dto.StoreInquiryAllDTO;
import com.campfood.src.store.dto.StoreInquiryDetailDTO;
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
                .storeName(store.getName())
                .storeTags(toTags(store.getStoreTags()))
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
                .toList();

        return StoreInquiryDetailDTO.builder()
                .storeName(store.getName())
                .storeTags(toTags(store.getStoreTags()))
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

    private List<Tag> toTags(List<StoreTag> storeTags) {
        return storeTags.stream()
                .map(StoreTag::getTag)
                .collect(Collectors.toList());
    }

    private StoreInquiryDetailDTO.OpenTimeInfo toOpenTimeInfo(StoreOpenTime storeOpenTime) {
        return StoreInquiryDetailDTO.OpenTimeInfo.builder()
                .day(storeOpenTime.getDayOfWeek())
                .openTime(storeOpenTime.getOpenTime())
                .breakTime(storeOpenTime.getBreakTime())
                .lastOrder(storeOpenTime.getLastOrder())
                .build();
    }
}
