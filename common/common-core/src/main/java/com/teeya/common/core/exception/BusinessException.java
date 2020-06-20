package com.teeya.common.core.exception;

import lombok.Data;

/**
 * 全局业务异常
 * @author zjh
 * @Date: 2020/3/11 13:10
 */
@Data
public class BusinessException extends RuntimeException {

    /**
     * 错误类型码
     */
    private Integer code;

    public BusinessException(ExceptionType exceptionType) {
        super(exceptionType.getMsg());
        this.code = exceptionType.getCode();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
