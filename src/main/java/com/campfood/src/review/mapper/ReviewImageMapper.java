package com.campfood.src.review.mapper;

import com.campfood.src.review.entity.Review;
import com.campfood.src.review.entity.ReviewImage;
import org.springframework.stereotype.Component;

@Component
public class ReviewImageMapper {
    public ReviewImage toReviewImage(Review review, String url) {
        return ReviewImage.builder()
                .review(review)
                .url(url)
                .build();
    }
}
