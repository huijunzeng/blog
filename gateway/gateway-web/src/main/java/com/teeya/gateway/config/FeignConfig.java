package com.teeya.gateway.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * feign调取服务时，携带上token
 * @Author: ZJH
 * @Date: 2020/2/24 13:29
 */

//@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        /*ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = null;
        String platform = null;
        if(attributes != null) {
            HttpServletRequest request1 = attributes.getRequest();
            token = request.getHeader("token");
            platform = request.getHeader("p_platform");
        }

        //添加token
        requestTemplate.header("token", token);
        requestTemplate.header("p_platform", platform);*/
    }
}
