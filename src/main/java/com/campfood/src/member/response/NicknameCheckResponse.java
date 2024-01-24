package com.campfood.src.member.response;

import com.campfood.common.result.ResultCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "닉네임 중복여부 응답 모델")
public class NicknameCheckResponse {
    @Schema(description = "Business 상태 코드")
    private final String code;
    @Schema(description = "응답 메세지")
    private final String message;
    @Schema(description = "응답 데이터")
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
