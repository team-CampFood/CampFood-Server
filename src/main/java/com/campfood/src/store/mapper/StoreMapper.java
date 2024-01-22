package com.campfood.src.store.mapper;

import com.campfood.src.member.entity.Member;
import com.campfood.src.store.dto.StoreInquiryByTagDTO;
import com.campfood.src.store.entity.Store;
import com.campfood.src.store.entity.StoreHeart;
import com.campfood.src.store.entity.StoreTag;
import com.campfood.src.store.entity.Tag;
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

    public StoreInquiryByTagDTO toInquiryByTagDTO(Store store) {
        
        List<Tag> tags = store.getStoreTags().stream()
                .map(StoreTag::getTag)
                .collect(Collectors.toList());

        return StoreInquiryByTagDTO.builder()
                .storeName(store.getName())
                .storeTags(tags)
                .storeImage(store.getImage())
                .naverRate(store.getNaverRate())
                .naverVisitedReviewCnt(store.getNaverVisitedReviewCnt())
                .naverBlogReviewCnt(store.getNaverBlogReviewCnt())
                .campFoodRate(store.getCampFoodRate())
                .camFoodReviewCnt(store.getCampFoodReviewCnt())
                .build();
    }
}
