package com.campfood.src.review.repository;

import com.campfood.src.member.entity.Member;
import com.campfood.src.review.entity.Review;
import com.campfood.src.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStore(Store store, Pageable pageable);
    Page<Review> findAllByMember(Member member, Pageable pageable);
    int countAllByMember(Member member);
}
