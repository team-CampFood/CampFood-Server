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


    //s Store
    ACTIVE_STORE_HAERT_SUCCESS(200, "S001", "좋아요를 활성화에 성공하였습니다."),
    INACTIVE_STORE_HEART_SUCCESS(200, "S002", "좋아요를 비활성화에 성공하였습니다."),
    INQUIRY_STORES_BY_TAG_SUCCESS(200, "S003", "특정 태그 가게 목록 조회에 성공하였습니다."),
    INQUIRY_STORES_BY_UNIVERSITY_SUCCESS(200, "S004", "특정 학교 가게 목록 조회에 성공하였습니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}
