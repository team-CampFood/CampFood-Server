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
public class StoreSearchByKeywordDTO {
    private Long storeId;
    private String storeName;
    private List<String> storeCategories;
    private String storeImage;
    private double campFoodRate;
    private int campFoodReviewCnt;
}
