package com.campfood.common.exception;

import com.campfood.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicatedLoginIdException extends RuntimeException{

    private final ErrorCode errorCode;

    public DuplicatedLoginIdException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
