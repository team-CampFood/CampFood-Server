package com.campfood.src.member.response;

import com.campfood.common.result.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(description = "로그인id 중복여부 응답 모델")
public class LoginIdCheckResponse {
    @ApiModelProperty(value = "Business 상태 코드")
    private final String code;
    @ApiModelProperty(value = "응답 메세지")
    private final String message;
    @ApiModelProperty(value = "응답 데이터")
    private final boolean data;


    public LoginIdCheckResponse(ResultCode resultCode, boolean data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public static LoginIdCheckResponse of(ResultCode resultCode, boolean data) {
        return new LoginIdCheckResponse(resultCode, data);
    }
}
