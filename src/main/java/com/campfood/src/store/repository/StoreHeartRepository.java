package com.campfood.src.store.repository;

import com.campfood.src.member.entity.Member;
import com.campfood.src.store.entity.Store;
import com.campfood.src.store.entity.StoreHeart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreHeartRepository extends JpaRepository<StoreHeart, Long> {
    Optional<StoreHeart> findByMemberAndStore(Member member, Store store);

    Page<StoreHeart> findAllByMemberAndIsCheckedIsTrue(Member member, Pageable pageable);
}
