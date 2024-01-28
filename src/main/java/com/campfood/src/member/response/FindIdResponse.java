package com.campfood.src.member.response;

import com.campfood.common.result.ResultCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "아이디 찾기 응답 모델")
public class FindIdResponse {
    @Schema(description = "Business 상태 코드")
    private final String code;
    @Schema(description = "응답 메세지")
    private final String message;
    @Schema(description = "응답 데이터")
    private final String data;


    public FindIdResponse(ResultCode resultCode, String data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public static FindIdResponse of(ResultCode resultCode, String data) {
        return new FindIdResponse(resultCode, data);
    }
}
