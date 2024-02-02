package com.campfood.src.review.repository;

import com.campfood.src.review.entity.Review;
import com.campfood.src.review.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

    List<ReviewImage> findAllByReview(Review review);
}
