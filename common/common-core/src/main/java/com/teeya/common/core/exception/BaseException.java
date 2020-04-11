package com.teeya.common.core.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {

    Integer code;

    String msg;

    public BaseException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
/**
     * 异常对应的错误类型
     */
    //private ErrorType errorType;

    /**
     * 默认是系统异常
     */
    /*public BaseException() {
        this.errorType = SystemErrorType.SYSTEM_ERROR;
    }

    public BaseException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }*/
}
