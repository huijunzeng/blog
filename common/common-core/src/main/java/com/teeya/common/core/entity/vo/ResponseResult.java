package com.teeya.common.core.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.teeya.common.core.exception.ExceptionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "响应体封装类")
public class ResponseResult<T> implements Serializable {

    @ApiModelProperty(value = "响应码", required = true)
    private Integer code;

    @ApiModelProperty(value = "响应信息描述")
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "响应数据体")
    private T data;

    public ResponseResult(ExceptionType exceptionType) {
        this.code = exceptionType.getCode();
        this.msg = exceptionType.getMsg();
    }

    public ResponseResult(ExceptionType exceptionType, T data) {
        this.code = exceptionType.getCode();
        this.msg = exceptionType.getMsg();
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}