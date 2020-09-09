package com.teeya.common.web.exception;

import com.teeya.common.core.entity.vo.R;
import com.teeya.common.core.exception.BusinessException;
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
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理类  按顺序优先处理
 */
@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {

    /**
     * AccessDeniedException 拒绝访问异常
     * 返回状态码:403
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public R badMethodExpressException(AccessDeniedException ex) {
        log.error("missing servlet request parameter exception:{}", ex.getMessage());
        return R.fail(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }

    /**
     * methodArgumentNotValidException 参数检验异常
     * 返回状态码:404
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public R methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error("handleMethodArgumentNotValidException start, uri:{}, caused by: {} ", request.getRequestURI(), ex);
        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrorList) {
            msg.append(", ").append(fieldError.getField()).append(fieldError.getDefaultMessage());
        }
        return R.fail(SystemExceptionEnums.PARAM_VALID_ERROR, msg == null ? SystemExceptionEnums.PARAM_VALID_ERROR.getMsg() : msg.substring(2));
    }

    /**
     * missingServletRequestParameterException 参数绑定异常
     * 返回状态码:404
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public R missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("missing servlet request parameter exception:{}", ex.getMessage());
        return R.fail(SystemExceptionEnums.PARAM_BIND_ERROR);
    }

    /**
     * noHandlerFoundException 请求找不到异常
     * 返回状态码:404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public R noHandlerFoundException(NoHandlerFoundException ex) {
        log.error("httpRequestMethodNotSupportedException:{}", ex.getMessage());
        return R.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    /**
     * HttpRequestMethodNotSupportedException 方法请求类型异常
     * 返回状态码:405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public R httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("httpRequestMethodNotSupportedException:{}", ex.getMessage());
        return R.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
    }

    /**
     * MultipartException 文件上传异常
     */
    @ExceptionHandler(value = {MultipartException.class})
    public R uploadFileLimitException(MultipartException ex) {
        log.error("upload file size limit:{}", ex.getMessage());
        return R.fail(SystemExceptionEnums.UPLOAD_FILE_SIZE_LIMIT);
    }

    /**
     * DuplicateKeyException 唯一键冲突
     */
    @ExceptionHandler(value = {DuplicateKeyException.class})
    public R duplicateKeyException(DuplicateKeyException ex) {
        log.error("primary key duplication exception:{}", ex.getMessage());
        return R.fail(SystemExceptionEnums.DUPLICATE_PRIMARY_KEY);
    }

    /**
     * ArithmeticException 算法异常
     */
    @ExceptionHandler(value = {ArithmeticException.class})
    public R arithmeticException(ArithmeticException ex) {
        log.error("arithmeticException:{}", ex.getMessage());
        return R.fail(SystemExceptionEnums.ARITHMETIC_ERROR);
    }

    /**
     * IllegalArgumentException 非法参数
     * 返回状态码:400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public R illegalArgumentException(IllegalArgumentException ex) {
        log.error("illegalArgumentException:{}", ex.getMessage());
        return R.fail(SystemExceptionEnums.PARAM_VALID_ERROR, ex.getMessage());
    }

    /**
     * SQLException sql异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class})
    public R SQLException(SQLException ex) {
        log.error("SQLException:{}", ex.getMessage());
        return R.fail(SystemExceptionEnums.PARAM_VALID_ERROR, ex.getMessage());
    }

    /**
     * ConstraintViolationException 单个参数校验
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public R constraintViolationException(ConstraintViolationException ex){
        log.error("constraintViolationException:{}", ex.getMessage());
        StringBuilder msg = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation constraintViolation : constraintViolations
             ) {
            msg.append(", ").append(constraintViolation.getMessage());
        }
        return R.fail(SystemExceptionEnums.PARAM_VALID_ERROR, msg == null ? SystemExceptionEnums.PARAM_VALID_ERROR.getMsg() : msg.substring(2));
    }

    /**
     * BusinessException 业务异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public R businessException(BusinessException ex) {
        log.error("businessException:{}", ex.getMessage());
        return R.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * 其他未知异常
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public R exception(Exception ex) {
        log.error("exception:{}", ex);
        return R.fail(SystemExceptionEnums.INTERNAL_SERVER_ERROR);
    }

    /**
     * 其他未知异常
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Throwable.class})
    public R throwable(Throwable throwable) {
        log.error("throwable:{}", throwable.getMessage());
        return R.fail(SystemExceptionEnums.INTERNAL_SERVER_ERROR);
    }

}