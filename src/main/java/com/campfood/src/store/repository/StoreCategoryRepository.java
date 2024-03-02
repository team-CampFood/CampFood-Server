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

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {

    Optional<StoreCategory> findByStoreAndCategory(Store store, Category category);
    List<StoreCategory> findAllByStore(Store store);

    @Query("SELECT sc.store FROM StoreCategory sc WHERE sc.category = :category")
    Page<Store> findAllByTag(@Param("category") Category category, Pageable pageable);;
}
