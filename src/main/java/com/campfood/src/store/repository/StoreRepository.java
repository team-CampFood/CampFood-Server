package com.campfood.src.store.repository;

import com.campfood.src.store.entity.Store;
import com.campfood.src.university.entity.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findById(Long id);

    Page<Store> findAllByUniversity(University university, Pageable pageable);
}
