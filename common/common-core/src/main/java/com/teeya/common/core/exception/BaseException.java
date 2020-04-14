package com.teeya.common.core.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {

    /**
     * 错误类型码
     */
    private Integer code;

    public BaseException(ExceptionType exceptionType) {
        super(exceptionType.getMsg());
        this.code = exceptionType.getCode();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
