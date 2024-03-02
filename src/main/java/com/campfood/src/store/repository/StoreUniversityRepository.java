package com.campfood.src.store.repository;

import com.campfood.src.store.entity.Store;
import com.campfood.src.store.entity.StoreUniversity;
import com.campfood.src.university.entity.University;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreUniversityRepository extends JpaRepository<StoreUniversity, Long> {
    Page<StoreUniversity>  findAllByUniversity(University university, Pageable pageable);

    boolean existsByUniversityAndStore(University university, Store store);

    List<StoreUniversity> findAllByStore(Store store);

    @Query("SELECT su.store FROM StoreUniversity su WHERE :university = su.university ORDER BY su.store.campFoodRate DESC")
    List<Store> findTop10ByUniversity(@Param("university") University university, Pageable pageable);
}
