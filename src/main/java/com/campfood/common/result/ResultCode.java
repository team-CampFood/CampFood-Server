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
    FIND_LOGINID_SUCCESS(200,"A010", "아이디 찾기에 성공하였습니다."),
    CHANGE_PASSWORD_SUCCESS(200, "A011", "비밀번호 변경에 성공하였습니다."),

    //mail
    EMAIL_SEND_SUCCESS(200,"M001", "이메일 전송에 성공하였습니다."),
    EMAIL_VERIFIED_SUCCESS (200,"M002", "인증번호가 일치합니다."),
    EMAIL_VERIFIED_FAILED(200,"M003", "인증번호가 일치하지 않습니다."),
    //member
    GET_MEMBER_INFO_SUCCESS(200, "M001", "멤버정보 조회에 성공하였습니다."),
    CHANGE_NICKNAME_SUCCESS(200,"M002", "닉네임 변경에 성공하였습니다."),
    CHANGE_PROFILE_SUCCESS(200,"M003", "프로필사진 변경에 성공하였습니다."),

    // Store
    ACTIVE_STORE_HAERT_SUCCESS(200, "S001", "좋아요를 활성화에 성공하였습니다."),
    INACTIVE_STORE_HEART_SUCCESS(200, "S002", "좋아요를 비활성화에 성공하였습니다."),
    INQUIRY_STORES_BY_TAG_SUCCESS(200, "S003", "특정 태그 가게 목록 조회에 성공하였습니다."),
    INQUIRY_STORES_BY_UNIVERSITY_SUCCESS(200, "S004", "특정 학교 가게 목록 조회에 성공하였습니다."),
    INQUIRY_STORE_DETAIL_SUCCESS(200, "S005", "특정 가게 상세 조회에 성공하였습니다."),
    SEARCH_STORES_BY_KEYWORD_SUCCESS(200, "S006", "가게 검색에 성공하였습니다."),
    INQUIRY_STORES_BY_POPULAR(200, "S007", "인기 가게 조회에 성공하였습니다."),

    // review
    CREATE_REVIEW_SUCCESS(200, "R001", "리뷰 생성에 성공하였습니다."),
    UPDATE_REVIEW_SUCCESS(200, "R002", "리뷰 수정에 성공하였습니다."),
    DELETE_REVIEW_SUCCESS(200, "R003", "리뷰 삭제에 성공하였습니다."),
    ACTIVE_REVIEW_HEART_SUCCESS(200, "R004", "리뷰 좋아요 활성화에 성공하였습니다."),
    INACTIVE_REVIEW_HEART_SUCCESS(200, "R005", "리뷰 좋아요 비활성화에 성공하였습니다."),
    INQUIRY_REVIEWS_BY_STORE_SUCCESS(200, "R006", "특정 가게의 리뷰 조회에 성공하였습니다."),

    ;
    private final int status;
    private final String code;
    private final String message;
}
