package com.campfood.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    Example(000,"example","example"),
    USER_NOT_EXIST(500,"code1","user not exist");

    final private int status;
    final private String errorCode;
    final private String message;
}
