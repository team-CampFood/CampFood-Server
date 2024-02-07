package com.campfood.src.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorePageResponse<T> {
    private List<T> stores;
    private boolean hasNext;
}
