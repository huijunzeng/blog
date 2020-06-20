package com.teeya.common.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 继承ExceptionType接口方法
@Getter
@AllArgsConstructor
public enum SystemExceptionEnums implements ExceptionType {

    SYSTEM_SUCCESS(200, "成功"),
    SYSTEM_FAILURE(400, "业务异常"),
    PARAM_TYPE_ERROR(400, "请求参数类型错误"),
    PARAM_BIND_ERROR(400, "请求参数绑定错误"),
    PARAM_VALID_ERROR(400, "参数校验失败"),
    PARAM_MISS(400, "缺少必要的请求参数"),
    MSG_NOT_READABLE(400, "消息不能读取"),
    UN_AUTHORIZED(401, "请求未授权"),
    CLIENT_UN_AUTHORIZED(401, "客户端请求未授权"),
    REQ_REJECT(403, "请求被拒绝"),
    NOT_FOUND(404, "404 没找到请求"),
    METHOD_NOT_SUPPORTED(405, "不支持当前请求方法"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    MEDIA_TYPE_NOT_SUPPORTED(415, "不支持当前媒体类型"),
    INTERNAL_SERVER_ERROR(500, "内部服务错误"),

    GATEWAY_NOT_FOUND_SERVICE(1002, "服务未找到"),
    GATEWAY_ERROR(1003, "网关异常"),
    GATEWAY_CONNECT_TIME_OUT(1004, "网关超时"),

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
