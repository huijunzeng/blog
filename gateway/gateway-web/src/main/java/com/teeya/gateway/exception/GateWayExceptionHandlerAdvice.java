package com.teeya.gateway.exception;

import com.teeya.common.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GateWayExceptionHandlerAdvice {

    /*@ExceptionHandler(value = {ResponseStatusException.class})
    public Map handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "服务内部错误");
        return map;
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public Map handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "服务内部错误");
        return map;
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map handle(NotFoundException ex) {
        log.error("not found exception:{}", ex.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "服务内部错误");
        return map;
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map handle(ExpiredJwtException ex) {
        log.error("ExpiredJwtException:{}", ex.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "服务内部错误");
        return map;
    }

    @ExceptionHandler(value = {SignatureException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map handle(SignatureException ex) {
        log.error("SignatureException:{}", ex.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "服务内部错误");
        return map;
    }

    @ExceptionHandler(value = {MalformedJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map handle(MalformedJwtException ex) {
        log.error("MalformedJwtException:{}", ex.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "服务内部错误");
        return map;
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map handle(RuntimeException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "服务内部错误");
        log.error("exception_map:{}", ex.getMessage());
        return map;
    }



    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map handle(Throwable throwable) {
        Map<String, Object> map = new HashMap<>();
        if (throwable instanceof ResponseStatusException) {
            map = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            map = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            map = handle((NotFoundException) throwable);
        } else if (throwable instanceof RuntimeException) {
            map = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            map = handle((Exception) throwable);
        }
        return map;
    }*/

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map handle(Exception ex) {
        log.error("exception:{}", ex);
        Map<String, Object> map = new HashMap<>();
        if (ex instanceof BusinessException){
            BusinessException businessException = (BusinessException) ex;
            map.put("code", 404);
            map.put("msg", businessException.getMessage());
        } else if (ex instanceof ArithmeticException) {
            map.put("code", 404);
            map.put("msg", "算法异常");
        } else {// 统一处理服务间内部调用异常
            map.put("code", 500);
            map.put("msg", "服务内部错误");
        }
        log.error("exception_map:{}", map.toString());
        return map;
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map handle(Throwable throwable) {
        Map<String, Object> map = new HashMap<>();
        /*if (throwable instanceof ResponseStatusException) {
            result = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            result = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            result = handle((NotFoundException) throwable);
        } else if (throwable instanceof RuntimeException) {
            result = handle((RuntimeException) throwable);
        } else*/
        if (throwable instanceof Exception) {
            map = handle((Exception) throwable);
        }
        return map;
    }
}
