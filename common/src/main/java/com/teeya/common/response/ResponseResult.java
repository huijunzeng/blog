package com.teeya.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.teeya.common.exception.ErrorType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/2/28 9:56
 */

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

    public ResponseResult(ErrorType errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
    }

    public ResponseResult(ErrorType errorType, T data) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
