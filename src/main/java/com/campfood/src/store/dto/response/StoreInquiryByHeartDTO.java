package com.campfood.src.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreInquiryByHeartDTO {
    private Long storeId;
    private String storeName;
    private String storeImage;
    private double naverRate;
    private int naverVisitedReviewCnt;
    private int naverBlogReviewCnt;
    private double campFoodRate;
    private int campFoodReviewCnt;
}
