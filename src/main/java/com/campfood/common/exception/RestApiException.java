package com.campfood.common.exception;

import com.campfood.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class RestApiException extends RuntimeException {

    private final ErrorCode errorCode;

    public RestApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
