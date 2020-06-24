package com.teeya.common.web.interceptor;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * Feign请求拦截器
 * 用于设置请求头/请求体信息  比如向下游服务传递token
 *
 * 解决场景：一个请求头携带token的请求，经gateway网关鉴权通过后路由到服务A，服务A可以获取到请求头token，假如服务A再调取服务B的方法，那么服务B就不能获取到请求头的token。
 *          这时就需要配置Feign请求拦截器，然后在@FeignClient注解内添加属性值 configuration = FeignRequestInterceptor.class
 *
 * @Author: ZJH
 * @Date: 2020/6/24 11:18
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

    /**
     * Authorization认证开头是"bearer "
     */
    private static final String BEARER = "Bearer ";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        // 设置请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                requestTemplate.header(name, value);
            }
        }

        // 看实际情况需不需要以下的设置，一般只设置请求头也就行了
        /*
        // 设置请求体，这里主要是为了传递 access_token，把access_token添加到请求头
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder body = new StringBuilder();
        if (parameterNames != null) {
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = request.getParameter(name);

                // 将 Token 加入请求头
                if ("access_token".equals(name)) {
                    requestTemplate.header(HttpHeaders.AUTHORIZATION, BEARER + value);
                }

                // 其它参数加入请求体
                else {
                    body.append(name).append("=").append(value).append("&");
                }
            }
        }

        // 设置请求体
        if (body.length() > 0) {
            // 去掉最后一位 & 符号
            body.deleteCharAt(body.length() - 1);
            requestTemplate.body(body.toString().getBytes(), Charset.defaultCharset());
        }*/
    }
}
