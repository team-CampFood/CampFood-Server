package com.campfood.src.review.repository;

import com.campfood.src.member.entity.Member;
import com.campfood.src.review.entity.Review;
import com.campfood.src.review.entity.ReviewHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewHeartRepository extends JpaRepository<ReviewHeart, Long> {
    Optional<ReviewHeart> findByMemberAndReview(Member member, Review review);
}
