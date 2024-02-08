package com.campfood.src.store.dto.response;

import com.campfood.src.store.entity.Category;
import com.campfood.src.store.entity.OpenDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreInquiryDetailDTO {
    private Long storeId;
    private String identificationId;
    private String storeName;
    private List<Category> storeCategories;
    private String storeImage;
    private double naverRate;
    private int naverVisitedReviewCnt;
    private int naverBlogReviewCnt;
    private double campFoodRate;
    private int camFoodReviewCnt;
    private String storeAddress;
    private List<OpenTimeInfo> openTimeInfos;
    private String storeNumber;

    @Getter
    @Builder
    public static class OpenTimeInfo {
        private OpenDay day;
        private String content;
    }
}
