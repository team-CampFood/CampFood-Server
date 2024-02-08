package com.campfood.src.store.repository;

import com.campfood.src.store.entity.Store;
import com.campfood.src.store.entity.StoreUniversity;
import com.campfood.src.university.entity.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreUniversityRepository extends JpaRepository<StoreUniversity, Long> {
    Page<StoreUniversity>  findAllByUniversity(University university, Pageable pageable);
}
