package com.campfood.common.result;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum     ResultCode {
    Example("e001", "example");

    private final String code;
    private final String message;
}
