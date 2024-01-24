package com.campfood.src.member.response;

import com.campfood.common.result.ResultCode;
import com.campfood.src.member.dto.MemberInfoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(description = "멤버 상세정보 응답 모델")
public class MemberInfoResponse {
    @ApiModelProperty(value = "Business 상태 코드")
    private final String code;
    @ApiModelProperty(value = "응답 메세지")
    private final String message;
    @ApiModelProperty(value = "응답 데이터")
    private final MemberInfoDto data;


    public MemberInfoResponse(ResultCode resultCode, MemberInfoDto data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public static MemberInfoResponse of(ResultCode resultCode, MemberInfoDto data) {
        return new MemberInfoResponse(resultCode, data);
    }
}
