package com.teeya.common.web.exception;

import com.google.common.collect.Maps;
import com.teeya.common.core.exception.SystemExceptionEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import java.util.Map;

@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public Map missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("missing servlet request parameter exception:{}", ex.getMessage());
        return this.getException(SystemExceptionEnums.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {MultipartException.class})
    public Map uploadFileLimitException(MultipartException ex) {
        log.error("upload file size limit:{}", ex.getMessage());
        return this.getException(SystemExceptionEnums.UPLOAD_FILE_SIZE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Map serviceException(MethodArgumentNotValidException ex) {
        log.error("service exception:{}", ex.getMessage());
        return this.getException(SystemExceptionEnums.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public Map duplicateKeyException(DuplicateKeyException ex) {
        log.error("primary key duplication exception:{}", ex.getMessage());
        return this.getException(SystemExceptionEnums.DUPLICATE_PRIMARY_KEY);
    }

    /*@ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map exception() {
        return this.getException(SystemExceptionEnums.INTERNAL_SERVER_ERROR);
    }*/

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map throwable() {
        return this.getException(SystemExceptionEnums.INTERNAL_SERVER_ERROR);
    }

    protected Map getException(SystemExceptionEnums systemExceptionEnums) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", systemExceptionEnums.getCode());
        map.put("msg", systemExceptionEnums.getMsg());
        return map;
    }
}