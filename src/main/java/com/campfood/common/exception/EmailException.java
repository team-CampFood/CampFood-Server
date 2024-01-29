package com.campfood.common.exception;

import com.campfood.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class EmailException extends RuntimeException{

    private final ErrorCode errorCode;

    public EmailException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
