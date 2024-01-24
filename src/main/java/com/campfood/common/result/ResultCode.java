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

    //s Store
    ACTIVE_STORE_HAERT_SUCCESS(200, "S001", "좋아요를 활성화에 성공하였습니다."),
    INACTIVE_STORE_HEART_SUCCESS(200, "S002", "좋아요를 비활성화에 성공하였습니다."),
    INQUIRY_STORES_BY_TAG_SUCCESS(200, "S003", "특정 태그 가게 목록 조회에 성공하였습니다."),
    INQUIRY_STORES_BY_UNIVERSITY_SUCCESS(200, "S004", "특정 학교 가게 목록 조회에 성공하였습니다."),
    INQUIRY_STORE_DETAIL_SUCCESS(200, "S005", "특정 가게 상세 조회에 성공하였습니다."),
    SEARCH_STORES_BY_KEYWORD_SUCCESS(200, "S006", "가게 검색에 성공하였습니다."),
    INQUIRY_STORES_BY_POPULAR(200, "S007", "인기 가게 조회에 성공하였습니다."),

    ;
    private final int status;
    private final String code;
    private final String message;
}
