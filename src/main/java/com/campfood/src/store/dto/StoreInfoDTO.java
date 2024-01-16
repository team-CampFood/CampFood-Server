package com.campfood.src.store.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreInfoDTO {
    @ApiModelProperty(value = "가게명", position = 1)
    private String name;
    //...
}
