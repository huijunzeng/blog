package com.teeya.common.core.exception;

import lombok.Getter;

@Getter
public enum CommonExceptionEnum implements ExceptionType {

    SYSTEM_SUCCESS(200, "成功"),
    SYSTEM_ERROR(500, "系统异常"),
    SYSTEM_BUSY(1001, "系统繁忙,请稍候再试"),

    GATEWAY_NOT_FOUND_SERVICE(1002, "服务未找到"),
    GATEWAY_ERROR(1003, "网关异常"),
    GATEWAY_CONNECT_TIME_OUT(1004, "网关超时"),

    ARGUMENT_NOT_VALID(2001, "请求参数校验不通过"),
    INVALID_TOKEN(2002, "无效token"),
    UPLOAD_FILE_SIZE_LIMIT(2003, "上传文件大小超过限制"),

    DUPLICATE_PRIMARY_KEY(3001,"唯一键冲突");

    /**
     * 错误类型码
     */
    private Integer code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    CommonExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
