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
    NEW_ACCESS_TOKEN_SUCCESS(200,"A003", "엑세스토큰 재발급에 성공하였습니다.");

    private final int status;
    private final String code;
    private final String message;
}
