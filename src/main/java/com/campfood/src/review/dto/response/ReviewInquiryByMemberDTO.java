package com.campfood.src.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInquiryByMemberDTO {
    private Long reviewId;
    private StoreInfo store;
    private double averageRate;
    private String content;
    private List<String> reviewImages;
    private LocalDate createdAt;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoreInfo {
        private Long storeId;
        private String name;
    }
}
