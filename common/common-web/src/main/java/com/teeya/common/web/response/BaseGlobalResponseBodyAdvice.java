package com.teeya.common.web.response;

import com.alibaba.fastjson.JSONObject;
import com.teeya.common.core.exception.SystemExceptionEnums;
import com.teeya.common.core.entity.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/** springboot单体应用统一正常响应结果格式处理  这里微服务架构用不上，统一交由gateway网关去统一处理返回的响应体
 * @Author: ZJH
 * @Date: 2020/2/18 14:32
 */

//@RestControllerAdvice
@Slf4j
public class BaseGlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * 对于返回的对象如果不是最终对象ResponseResult，则需要包装一下
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("start=============");
        if(!(o instanceof R)) {
            Object newObject = JSONObject.toJSON(o);
            R r = new R(SystemExceptionEnums.SYSTEM_SUCCESS, newObject);
            log.info("responseResult============={}", r.toString());
            log.info("toJSON(responseResult)============={}", JSONObject.toJSON(r));
            return r;
        }
        return o;
    }
}
