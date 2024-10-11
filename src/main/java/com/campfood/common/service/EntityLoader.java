package com.campfood.common.service;

import com.campfood.src.store.dto.response.StoreNearestDTO;

public interface EntityLoader<T, ID> {
    T loadEntity(ID id);

    StoreNearestDTO inquiryNearestStores(Double latitude, Double longitude);
}