package com.campfood.src.store.dto;

import com.campfood.src.store.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreInquiryAllDTO {
    private String storeName;
    private List<Tag> storeTags;
    private String storeImage;
    private double naverRate;
    private int naverVisitedReviewCnt;
    private int naverBlogReviewCnt;
    private double campFoodRate;
    private int camFoodReviewCnt;
}
