package com.campfood.src.review.mapper;

import com.campfood.src.review.dto.request.ReviewCreateDTO;
import com.campfood.src.review.entity.Review;
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
}
