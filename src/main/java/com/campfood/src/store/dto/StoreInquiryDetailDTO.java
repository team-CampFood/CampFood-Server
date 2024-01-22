package com.campfood.src.store.dto;

import com.campfood.src.store.entity.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreInquiryDetailDTO {
    private String storeName;
    private List<Tag> storeTags;
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
        private DayOfWeek day;
        private String openTime;
        private String breakTime;
        private String lastOrder;
    }
}
