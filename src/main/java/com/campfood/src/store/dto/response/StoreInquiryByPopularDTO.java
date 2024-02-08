package com.campfood.src.store.dto.response;

import com.campfood.src.store.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreInquiryByPopularDTO {
    private Long storeId;
    private String storeName;
    private String storeImage;
    private Category storeCategory;
}
