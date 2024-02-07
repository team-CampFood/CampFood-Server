package com.campfood.src.store.repository;

import com.campfood.src.store.entity.Category;
import com.campfood.src.store.entity.Store;
import com.campfood.src.store.entity.StoreCategory;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {

    @Query("SELECT sc.store FROM StoreCategory sc WHERE sc.category = :category")
    Page<Store> findAllByTag(@Param("category") Category category, Pageable pageable);;
}
