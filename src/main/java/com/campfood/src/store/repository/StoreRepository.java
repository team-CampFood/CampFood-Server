package com.campfood.src.store.repository;

import com.campfood.src.store.entity.Store;
import com.campfood.src.university.entity.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findById(Long id);
    Page<Store> findAllByUniversity(University university, Pageable pageable);

    @Query("SELECT s FROM Store s WHERE s.name LIKE %:keyword%")
    Page<Store> findByKeyword(String keyword, Pageable pageable);
}
