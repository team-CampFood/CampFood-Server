package com.campfood.src.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class StoreNearestDTO {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
}
