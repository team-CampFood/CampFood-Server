package com.campfood.src.review.mapper;

import com.campfood.src.member.entity.Member;
import com.campfood.src.review.dto.request.ReviewCreateDTO;
import com.campfood.src.review.entity.Review;
import com.campfood.src.review.entity.ReviewHeart;
import com.campfood.src.review.entity.ReviewImage;
import com.campfood.src.store.entity.Store;
import org.springframework.stereotype.Component;

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
}
