package com.campfood.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //Auth
    INPUT_NOT_FOUND(400,"A001", "존재하지 않는 입력입니다."),
    MEMBER_NOT_EXIST(500,"A002","유저가 존재하지 않습니다."),
    REFRESH_TOKEN_EXPIRED(401,"AOO3", "리프래시토큰이 만료되었습니다."),
    TOKEN_NULL(401, "A004", "토큰이 존재하지 않습니다."),
    ACCESS_TOKEN_EXPIRED(401, "A005", "엑세스토큰이 만료되었습니다."),
    ACCESS_TOKEN_MISMATCH(401, "A006", "엑세스토큰이 일치하지 않습니다.");


    final private int status;
    final private String errorCode;
    final private String message;
}
