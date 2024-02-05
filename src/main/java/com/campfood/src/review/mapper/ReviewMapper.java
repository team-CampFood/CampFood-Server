package com.campfood.src.review.mapper;

import com.campfood.src.member.entity.Member;
import com.campfood.src.review.dto.request.ReviewCreateDTO;
import com.campfood.src.review.dto.response.ReviewInquiryByMemberDTO;
import com.campfood.src.review.dto.response.ReviewInquiryByStoreDTO;
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

    public ReviewInquiryByStoreDTO toReviewInquiryByStoreDTO(Review review, double averageRate, List<String> reviewImages,
                                                             ReviewInquiryByStoreDTO.WriterInfo writer) {
        return ReviewInquiryByStoreDTO.builder()
                .reviewId(review.getId())
                .writer(writer)
                .averageRate(averageRate)
                .content(review.getContent())
                .reviewImages(reviewImages)
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public ReviewInquiryByStoreDTO.WriterInfo toWriterInfo(Member member, String profileImage, int reviewCnt) {
        return ReviewInquiryByStoreDTO.WriterInfo.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .profileImage(profileImage)
                .reviewCnt(reviewCnt)
                .averageRate(member.getAverageRate())
                .build();
    }

    public ReviewInquiryByMemberDTO toReviewInquiryByMemberDTO(Review review, double averageRate, List<String> reviewImages,
                                                               ReviewInquiryByMemberDTO.StoreInfo store) {
        return ReviewInquiryByMemberDTO.builder()
                .reviewId(review.getId())
                .store(store)
                .averageRate(averageRate)
                .reviewImages(reviewImages)
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public ReviewInquiryByMemberDTO.StoreInfo toStoreInfo(Store store) {
        return ReviewInquiryByMemberDTO.StoreInfo.builder()
                .storeId(store.getId())
                .name(store.getName())
                .build();
    }
}
