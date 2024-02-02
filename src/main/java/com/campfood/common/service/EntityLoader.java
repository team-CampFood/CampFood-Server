package com.campfood.common.service;

public interface EntityLoader<T, ID> {
    T loadEntity(ID id);
}