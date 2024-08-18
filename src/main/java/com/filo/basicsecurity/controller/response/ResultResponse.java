package com.filo.basicsecurity.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponse<T> {
    private boolean success;
    private String code;
    private String message;
    private T data;

    public static final String CODE_SUCCEED = "SUCCEED";
    public static final String CODE_FAILED = "FAILED";

    public ResultResponse(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultResponse<T> ok(T data) {
        return new ResultResponse<>(true, CODE_SUCCEED, null, data);
    }

    public static <T> ResultResponse<T> fail(String message) {
        return new ResultResponse<>(false, CODE_FAILED, message, null);
    }
}