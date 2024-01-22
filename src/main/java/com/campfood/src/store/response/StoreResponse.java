package com.campfood.src.store.response;

import com.campfood.common.result.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(description = "가게 상세정보 응답 모델")
public class StoreResponse<T> {

    @ApiModelProperty(value = "Business 상태 코드")
    private final String code;
    @ApiModelProperty(value = "응답 메세지")
    private final String message;
    @ApiModelProperty(value = "응답 데이터")
    private final T data;


    public StoreResponse(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public static <T> StoreResponse<T> of(ResultCode resultCode, T data) {
        return new StoreResponse<>(resultCode, data);
    }
}
