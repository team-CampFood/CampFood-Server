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
public class StoreSearchByKeywordDTO {
    private Long storeId;
    private String storeName;
    private List<Tag> storeTags;
    private String storeImage;
    private double campFoodRate;
    private int campFoodReviewCnt;
}
