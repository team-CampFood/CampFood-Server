package com.campfood.src.store.dto.response;

import com.campfood.src.store.entity.Category;
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
    private Long storeId;
    private String storeName;
    private List<Category> storeCategories;
    private String storeImage;
    private double naverRate;
    private int naverVisitedReviewCnt;
    private int naverBlogReviewCnt;
    private double campFoodRate;
    private int camFoodReviewCnt;
}
