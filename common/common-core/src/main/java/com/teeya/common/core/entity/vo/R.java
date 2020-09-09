package com.teeya.common.core.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.teeya.common.core.exception.ExceptionType;
import com.teeya.common.core.exception.SystemExceptionEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应体封装类
 * @author zjh
 * @Date: 2020/3/11 13:10
 */
@Data
@ApiModel(value = "统一响应体封装类")
public class R<T> implements Serializable {

    @ApiModelProperty(value = "响应码", required = true)
    private Integer code;

    @ApiModelProperty(value = "响应信息描述")
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "响应数据体")
    private T data;

    public R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     *
     * @param exceptionType 业务操作响应码
     */
    public R(ExceptionType exceptionType) {
        this(exceptionType.getCode(), exceptionType.getMsg(), null);
    }

    public R(ExceptionType exceptionType, T data) {
        this(exceptionType.getCode(), exceptionType.getMsg(), data);
    }

    public R(ExceptionType exceptionType, String msg) {
        this(exceptionType.getCode(), msg);
    }

    public R(ExceptionType exceptionType, String msg, T data) {
        this(exceptionType.getCode(), msg, data);
    }

    public R(Integer code, String msg) {
        this(code, msg, null);
    }

    public static <T> R<T> data(Integer code, String msg, T data) {
        return new R<>(code, msg, data);
    }

    public static <T> R<T> data(String msg, T data) {
        return new R<>(SystemExceptionEnums.SYSTEM_SUCCESS, msg, data);
    }

    public static <T> R<T> data(T data) {
        return new R<>(SystemExceptionEnums.SYSTEM_SUCCESS, data);
    }


    public static <T> R<T> success(ExceptionType exceptionType) {
        return new R<>(exceptionType);
    }

    public static <T> R<T> success(String msg) {
        return new R<>(SystemExceptionEnums.SYSTEM_SUCCESS, msg);
    }

    public static <T> R<T> fail(ExceptionType exceptionType) {
        return new R<>(exceptionType);
    }

    public static <T> R<T> fail(ExceptionType exceptionType, String msg) {
        return new R<>(exceptionType.getCode(), msg);
    }

    public static <T> R<T> fail(String msg) {
        return new R<>(SystemExceptionEnums.SYSTEM_FAILURE, msg);
    }

    public static <T> R<T> fail(Integer code, String msg) {
        return new R<>(code, msg);
    }

    /**
     * @param flag 成功状态
     * @param <T>  T 泛型标记
     * @return
     */
    public static <T> R<T> status(boolean flag) {
        return flag ? success(SystemExceptionEnums.SYSTEM_SUCCESS) : fail(SystemExceptionEnums.SYSTEM_FAILURE);
    }
}