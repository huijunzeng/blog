package com.teeya.common.web.exception;

import com.google.common.collect.Maps;
import com.teeya.common.core.exception.BaseException;
import com.teeya.common.core.exception.CommonExceptionEnum;
import com.teeya.common.core.exception.ExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {

    protected Map<String, Object> getException(ExceptionType exceptionType) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", exceptionType.getCode());
        map.put("msg", exceptionType.getMsg());
        return map;
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public Map<String, Object> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("missing servlet request parameter exception:{}", ex.getMessage());
        return this.getException(CommonExceptionEnum.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {MultipartException.class})
    public Map<String, Object> uploadFileLimitException(MultipartException ex) {
        log.error("upload file size limit:{}", ex.getMessage());
        return this.getException(CommonExceptionEnum.UPLOAD_FILE_SIZE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Map<String, Object> serviceException(MethodArgumentNotValidException ex) {
        log.error("service exception:{}", ex.getMessage());
        return this.getException(CommonExceptionEnum.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public Map<String, Object> duplicateKeyException(DuplicateKeyException ex) {
        log.error("primary key duplication exception:{}", ex.getMessage());
        return this.getException(CommonExceptionEnum.DUPLICATE_PRIMARY_KEY);
    }

    /*@ExceptionHandler(value = {BaseException.class})
    public Map<String, Object> baseException(BaseException ex) {
        log.error("base exception:{}", ex.getMessage());
        return Result.fail(ex.getErrorType());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> exception() {
        return Result.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> throwable() {
        return Result.fail();
    }*/
}