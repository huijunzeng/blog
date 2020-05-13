package com.teeya.common.web.exception;

import com.teeya.common.core.entity.vo.CommonResponse;
import com.teeya.common.core.exception.SystemExceptionEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常处理类  按顺序优先处理
 */
@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MissingServletRequestParameterException.class, MethodArgumentNotValidException.class})
    public CommonResponse methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error("handleMethodArgumentNotValidException start, uri:{}, caused by: {} ", request.getRequestURI(), ex);
        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrorList) {
            msg.append(", ").append(fieldError.getField()).append(fieldError.getDefaultMessage());
        }
        return CommonResponse.fail(SystemExceptionEnums.ARGUMENT_NOT_VALID, msg == null ? SystemExceptionEnums.ARGUMENT_NOT_VALID.getMsg() : msg.substring(2));
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public CommonResponse missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("missing servlet request parameter exception:{}", ex.getMessage());
        return CommonResponse.fail(SystemExceptionEnums.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public CommonResponse noHandlerFoundException(NoHandlerFoundException ex) {
        log.error("httpRequestMethodNotSupportedException:{}", ex.getMessage());
        return CommonResponse.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public CommonResponse httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("httpRequestMethodNotSupportedException:{}", ex.getMessage());
        return CommonResponse.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
    }

    @ExceptionHandler(value = {MultipartException.class})
    public CommonResponse uploadFileLimitException(MultipartException ex) {
        log.error("upload file size limit:{}", ex.getMessage());
        return CommonResponse.fail(SystemExceptionEnums.UPLOAD_FILE_SIZE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public CommonResponse methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("methodArgumentNotValidException:{}", ex.getMessage());
        return CommonResponse.fail(SystemExceptionEnums.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public CommonResponse duplicateKeyException(DuplicateKeyException ex) {
        log.error("primary key duplication exception:{}", ex.getMessage());
        return CommonResponse.fail(SystemExceptionEnums.DUPLICATE_PRIMARY_KEY);
    }

    @ExceptionHandler(value = {ArithmeticException.class})
    public CommonResponse arithmeticException(ArithmeticException ex) {
        log.error("arithmeticException:{}", ex.getMessage());
        return CommonResponse.fail(SystemExceptionEnums.ARITHMETIC_ERROR);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public CommonResponse illegalArgumentException(IllegalArgumentException ex) {
        log.error("illegalArgumentException:{}", ex.getMessage());
        return CommonResponse.fail(SystemExceptionEnums.ARGUMENT_NOT_VALID, ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse exception() {
        return CommonResponse.fail(SystemExceptionEnums.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse throwable() {
        return CommonResponse.fail(SystemExceptionEnums.INTERNAL_SERVER_ERROR);
    }

}