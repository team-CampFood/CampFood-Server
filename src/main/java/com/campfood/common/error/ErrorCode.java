package com.campfood.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //Auth
    INPUT_NOT_FOUND(400,"E001", "존재하지 않는 입력입니다."),
    MEMBER_NOT_EXIST(500,"E002","유저가 존재하지 않습니다."),
    REFRESH_TOKEN_EXPIRED(401,"EOO3", "리프래시토큰이 만료되었습니다."),
    TOKEN_NULL(401, "E004", "토큰이 존재하지 않습니다."),
    ACCESS_TOKEN_EXPIRED(401, "E005", "엑세스토큰이 만료되었습니다."),
    ACCESS_TOKEN_MISMATCH(401, "E006", "엑세스토큰이 일치하지 않습니다."),
    PASSWORD_MISMATCH(400, "E007", "비밀번호가 일치하지 않습니다."),

    //Mail
    UNABLE_TO_SEND_EMAIL(401, "M001", "이메일 전송에 실패하였습니다."),
    ALREADY_REGISTERED_EMAIL(401, "M002", "이미 등록된 이메일입니다."),
    CREATE_AUTHCODE_FAILED(401,"M003", "이메일 인증코드 생성에 실패하였습니다."),

    // Store
    STORE_NOT_EXIST(500, "S001", "존재하지 않는 가게입니다."),

    // University
    UNIVERSITY_NOT_EXIST(500, "U001", "존재하지 않는 대학교입니다."),
    ;

    final private int status;
    final private String errorCode;
    final private String message;
}
