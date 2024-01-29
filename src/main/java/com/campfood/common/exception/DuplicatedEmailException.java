package com.campfood.common.exception;

import com.campfood.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicatedEmailException extends RuntimeException{

    private final ErrorCode errorCode;

    public DuplicatedEmailException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
