package com.campfood.common.exception;

import com.campfood.common.error.ErrorCode;

public class PasswordMismatchException extends RuntimeException{
    private final ErrorCode errorCode;

    public PasswordMismatchException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}