package com.campfood.src.store.response;

import com.campfood.common.result.ResultCode;
import com.campfood.src.store.dto.StoreInfoDTO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

@Getter
@ApiModel(description = "가게 상세정보 응답 모델")
public class StoreInfoResponse {

    @ApiModelProperty(value = "Business 상태 코드")
    private final String code;
    @ApiModelProperty(value = "응답 메세지")
    private final String message;
    @ApiModelProperty(value = "응답 데이터")
    private final StoreInfoDTO data;


    public StoreInfoResponse(ResultCode resultCode, StoreInfoDTO data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public static StoreInfoResponse of(ResultCode resultCode, StoreInfoDTO data) {
        return new StoreInfoResponse(resultCode, data);
    }
}
