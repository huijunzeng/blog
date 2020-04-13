package com.teeya.common.web.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.teeya.common.core.exception.ExceptionType;
import com.teeya.common.core.exception.CommonExceptionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;

@ApiModel(description = "rest请求的返回模型，所有rest正常都返回该类的对象")
@Getter
public class Result<T> {

    public static final Integer SUCCESSFUL_CODE = 200;
    public static final String SUCCESSFUL_MESG = "处理成功";

    @ApiModelProperty(value = "处理结果code", required = true)
    private Integer code;
    @ApiModelProperty(value = "处理结果描述信息")
    private String msg;
    @ApiModelProperty(value = "请求结果生成时间戳")
    private Instant time;
    @ApiModelProperty(value = "处理结果数据信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * @param exceptionType
     */
    public Result(ExceptionType exceptionType) {
        this.code = exceptionType.getCode();
        this.msg = exceptionType.getMsg();
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * @param exceptionType
     * @param data
     */
    public Result(ExceptionType exceptionType, T data) {
        this(exceptionType);
        this.data = data;
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param msg
     * @param data
     */
    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result success(Object data) {
        return new Result(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static Result fail() {
        return new Result(CommonExceptionEnum.SYSTEM_ERROR);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @param baseException
     * @return Result
     */
    /*public static Result fail(BaseException baseException) {
        return fail(baseException, null);
    }*/

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    /*public static Result fail(BaseException baseException, Object data) {
        return new Result<>(baseException.getErrorType(), data);
    }*/

    /**
     * 系统异常类并返回结果数据
     *
     * @param exceptionType
     * @param data
     * @return Result
     */
    public static Result fail(ExceptionType exceptionType, Object data) {
        return new Result<>(exceptionType, data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param exceptionType
     * @return Result
     */
    public static Result fail(ExceptionType exceptionType) {
        return Result.fail(exceptionType, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(Object data) {
        return new Result<>(CommonExceptionEnum.SYSTEM_ERROR, data);
    }


    /**
     * 成功code=200
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }
}
