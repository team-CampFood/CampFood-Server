package com.campfood.common.exception;

import com.campfood.common.error.ErrorCode;

public class MemberNotExistException extends RuntimeException{
    private final ErrorCode errorCode;

    public MemberNotExistException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
