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
public class ReviewInquiryByStoreDTO {
    private Long reviewId;
    private WriterInfo writer;
    private double averageRate;
    private String content;
    private List<String> reviewImages;
    private LocalDate createdAt;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WriterInfo {
        private Long memberId;
        private String nickname;
        private String profileImage;
        private int reviewCnt;
        private double averageRate;
    }
}
