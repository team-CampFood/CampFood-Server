package com.campfood.src.store.repository;

import com.campfood.src.store.entity.OpenDay;
import com.campfood.src.store.entity.Store;
import com.campfood.src.store.entity.StoreOpenTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreOpenTimeRepository extends JpaRepository<StoreOpenTime, Long> {

    Optional<StoreOpenTime> findByStoreAndDay(Store store, OpenDay day);

    List<StoreOpenTime> findAllByStore(Store store);
}
