package com.campfood.src.review.mapper;

import com.campfood.src.member.entity.Member;
import com.campfood.src.review.dto.request.ReviewCreateDTO;
import com.campfood.src.review.dto.response.ReviewInquiryAllDTO;
import com.campfood.src.review.entity.Review;
import com.campfood.src.review.entity.ReviewHeart;
import com.campfood.src.review.entity.ReviewImage;
import com.campfood.src.store.entity.Store;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMapper {

    public Review toReview(Store store, ReviewCreateDTO request) {
        return Review.builder()
                .store(store)
                .content(request.getContent())
                .taste_rate(request.getTasteRate())
                .cost_effectiveness_rate(request.getCostEffectivenessRate())
                .service_rate(request.getServiceRate())
                .clean_rate(request.getCleanRate())
                .build();
    }

    public ReviewImage toReviewImage(Review review, String url) {
        return ReviewImage.builder()
                .review(review)
                .url(url)
                .build();
    }

    public ReviewHeart toReviewHeart(Review review, Member member) {
        return ReviewHeart.builder()
                .review(review)
                .member(member)
                .isChecked(false)
                .build();
    }

    public ReviewInquiryAllDTO toReviewInquiryAllDTO(Review review, double averageRate,
                                                     List<String> reviewImages, ReviewInquiryAllDTO.Writer writer) {
        return ReviewInquiryAllDTO.builder()
                .reviewId(review.getId())
                .writer(writer)
                .averageRate(averageRate)
                .content(review.getContent())
                .reviewImages(reviewImages)
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public ReviewInquiryAllDTO.Writer toWriter(Member member, String profileImage, int reviewCnt) {
        return ReviewInquiryAllDTO.Writer.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .profileImage(profileImage)
                .reviewCnt(reviewCnt)
                .averageRate(member.getAverageRate())
                .build();
    }
}
