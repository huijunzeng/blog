package com.teeya.common.handler;

import com.alibaba.fastjson.JSONObject;
import com.teeya.common.exception.SystemErrorType;
import com.teeya.common.response.ResponseResult;
import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: ZJH
 * @Date: 2020/2/18 14:32
 */
//@ControllerAdvice
public class BaseGlobalResponseBodyAdvice /*implements ResponseBodyAdvice<Object>*/ {
    /*@Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    //对于返回的对象如果不是最终对象ResponseResult，则选包装一下
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(!(o instanceof ResponseResult)) {
            Object o2 = JSONObject.toJSON(o);
            ResponseResult responseResult = new ResponseResult(SystemErrorType.SYSTEM_SUCCESS, o2);
            //因为handler处理类的返回类型是String，为了保证一致性，这里需要将ResponseResult转回去
            Object o1 = JSONObject.toJSONString(responseResult);
            System.out.println(JSONObject.toJSON(responseResult));
            System.out.println(responseResult.toString());
            return responseResult;
        }
        return o;
    }*/
}
