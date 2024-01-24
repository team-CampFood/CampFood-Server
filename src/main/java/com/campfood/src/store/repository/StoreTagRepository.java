package com.campfood.src.store.repository;

import com.campfood.src.store.entity.Store;
import com.campfood.src.store.entity.StoreCategory;
import com.campfood.src.store.entity.Category;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreTagRepository extends JpaRepository<StoreCategory, Long> {

    @Query("SELECT st.store FROM StoreCategory st WHERE st.tag = :tag")
    Page<Store> findAllByTag(@Param("tag") Category category, Pageable pageable);;
}
