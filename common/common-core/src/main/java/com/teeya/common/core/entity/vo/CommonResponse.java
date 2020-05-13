package com.teeya.common.core.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.teeya.common.core.exception.ExceptionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "统一响应体封装类")
public class CommonResponse<T> implements Serializable {

    @ApiModelProperty(value = "响应码", required = true)
    private Integer code;

    @ApiModelProperty(value = "响应信息描述")
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "响应数据体")
    private T data;

    public CommonResponse(ExceptionType exceptionType) {
        this.code = exceptionType.getCode();
        this.msg = exceptionType.getMsg();
    }

    public CommonResponse(ExceptionType exceptionType, T data) {
        this.code = exceptionType.getCode();
        this.msg = exceptionType.getMsg();
        this.data = data;
    }

    public CommonResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static CommonResponse success(Integer code, String msg, Object data) {
        return new CommonResponse<>(code, msg, data);
    }

    public static CommonResponse fail(ExceptionType exceptionType) {
        return new CommonResponse<>(exceptionType);
    }

    public static CommonResponse fail(ExceptionType exceptionType, String msg) {
        return new CommonResponse<>(exceptionType.getCode(), msg);
    }

    public static CommonResponse fail(Integer code, String msg) {
        return new CommonResponse<>(code, msg);
    }
}