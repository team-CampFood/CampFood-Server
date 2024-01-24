package com.campfood.src.store.dto;

import com.campfood.src.store.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreInquiryPopularDTO {
    private Long storeId;
    private String storeImage;
    private Category storeCategory;
}
