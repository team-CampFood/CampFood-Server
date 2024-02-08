package com.campfood.src.store.repository;

import com.campfood.src.store.entity.StoreOpenTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreOpenTimeRepository extends JpaRepository<StoreOpenTime, Long> {
}
