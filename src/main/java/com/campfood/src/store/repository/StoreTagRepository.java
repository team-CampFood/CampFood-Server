package com.campfood.src.store.repository;

import com.campfood.src.store.entity.Store;
import com.campfood.src.store.entity.StoreTag;
import com.campfood.src.store.entity.Tag;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreTagRepository extends JpaRepository<StoreTag, Long> {

    @Query("SELECT st.store FROM StoreTag st WHERE st.tag = :tag")
    Page<Store> findAllByTag(@Param("tag") Tag tag, Pageable pageable);;
}
