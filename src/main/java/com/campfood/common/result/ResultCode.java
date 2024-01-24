package com.campfood.common.result;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum     ResultCode {
    Example(000, "e001", "example"),
    //Auth
    LOGIN_SUCCESS(200, "A001", "로그인에 성공하였습니다."),
    SIGNIN_SUCCESS(200,"A002", "회원가입에 성공하였습니다."),
    NEW_ACCESS_TOKEN_SUCCESS(200,"A003", "엑세스토큰 재발급에 성공하였습니다."),
    VALID_NICKNAME(200, "A004", "사용가능한 닉네임입니다."),
    INVALID_NICKNAME(200, "A005", "사용가능하지 않은 닉네임입니다."),
    VALID_LOGIN_ID(200, "A006", "사용가능한 id입니다."),
    INVALID_LOGIN_ID(200, "A007", "사용가능하지 않은 id입니다."),
    WITHDRAWAL_SUCCESS(200,"A008", "회원탈퇴에 성공하였습니다."),
    LOGOUT_SUCCESS(200,"A009", "로그아웃에 성공하였습니다."),

    //member
    GET_MEMBER_INFO_SUCCESS(200, "M001", "멤버정보 조회에 성공하였습니다.");

    private final int status;
    private final String code;
    private final String message;
}
