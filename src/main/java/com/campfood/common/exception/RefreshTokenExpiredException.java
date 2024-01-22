package com.campfood.common.exception;

import com.campfood.common.error.ErrorCode;

public class RefreshTokenExpiredException extends RuntimeException{
    private final ErrorCode errorCode;

    public RefreshTokenExpiredException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}

