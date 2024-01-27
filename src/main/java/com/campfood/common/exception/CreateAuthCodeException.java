package com.campfood.common.exception;

import com.campfood.common.error.ErrorCode;

public class CreateAuthCodeException extends RuntimeException{

    private final ErrorCode errorCode;

    public CreateAuthCodeException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
