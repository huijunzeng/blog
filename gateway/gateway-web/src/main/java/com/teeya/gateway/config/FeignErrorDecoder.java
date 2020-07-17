package com.teeya.gateway.config;

import com.teeya.common.core.exception.BusinessException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


/**
 *  自定义异常解析器
 *  由于feign对400-599之间的响应状态码做了默认处理，只会返回类似feign.FeignException$Unauthorized: status 401 reading AuthenticationProvider#hasPermission(String,String,String)的信息，所以我们需要自定义异常解析器，捕捉响应码并继续向上抛，让全局异常类去做处理
 *  关键类目录路径：\io\github\openfeign\feign-core\10.4.0\feign-core-10.4.0.jar!\feign\codec\ErrorDecoder.class
 *  对于401响应状态码，feign处理异常默认是先String message = String.format("status %s reading %s", response.status(), methodKey)，后new FeignException.Unauthorized(message, request, body)，并没有把status单独抛出来，以至于不能统一处理异常
 *  解决办法：实现feign的ErrorDecoder接口并且重写decode方法
 *
 * @Author: ZJH
 * @Date: 2020/3/2 14:08
 */
@Configuration
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    /**
     * feign调取内部接口异常时的处理
     * @param methodKey
     * @param response
     * @return
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String message = Util.toString(response.body().asReader());
            log.info("methodKey: {}", methodKey);
            log.info("response: {}", response);
            log.info("response.body(): {}", message);
            log.info("response status: {}", response.status());
            return new BusinessException(response.status(), methodKey);
        } catch (Exception e) {
            log.error("decode Exception: {}", e.getMessage());
        }
        log.info("BaseException: {}, {}", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
        return new BusinessException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
