package com.teeya.common.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 继承ExceptionType接口方法
@Getter
@AllArgsConstructor
public enum SystemExceptionEnums implements ExceptionType {

    SYSTEM_SUCCESS(200, "成功"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    INTERNAL_SERVER_ERROR(500, "内部服务错误"),

    GATEWAY_NOT_FOUND_SERVICE(1002, "服务未找到"),
    GATEWAY_ERROR(1003, "网关异常"),
    GATEWAY_CONNECT_TIME_OUT(1004, "网关超时"),

    ARGUMENT_NOT_VALID(2001, "请求参数校验不通过"),
    INVALID_TOKEN(2002, "无效token"),
    UPLOAD_FILE_SIZE_LIMIT(2003, "上传文件大小超过限制"),

    DUPLICATE_PRIMARY_KEY(3001,"唯一键冲突"),
    ARITHMETIC_ERROR(4001,"算法错误");

    /**
     * 错误类型码
     */
    private Integer code;

    /**
     * 错误类型描述信息
     */
    private String msg;

}
