package com.campfood.src.member.response;

import com.campfood.common.result.ResultCode;
import com.campfood.src.store.dto.StoreInfoDTO;
import com.campfood.src.store.response.StoreInfoResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(description = "닉네임 중복여부 응답 모델")
public class NicknameCheckResponse {
    @ApiModelProperty(value = "Business 상태 코드")
    private final String code;
    @ApiModelProperty(value = "응답 메세지")
    private final String message;
    @ApiModelProperty(value = "응답 데이터")
    private final boolean data;


    public NicknameCheckResponse(ResultCode resultCode, boolean data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public static NicknameCheckResponse of(ResultCode resultCode, boolean data) {
        return new NicknameCheckResponse(resultCode, data);
    }
}
