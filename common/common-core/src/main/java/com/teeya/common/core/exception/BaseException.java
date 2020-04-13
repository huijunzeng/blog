package com.teeya.common.core.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    /*Integer code;

    String msg;

    public BaseException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }*/
    /**
     * 异常对应的错误类型
     */
    private final ExceptionType exceptionType;

    /**
     * 默认是系统异常
     */
    public BaseException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public BaseException(ExceptionType exceptionType, String message, Throwable cause) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }
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
