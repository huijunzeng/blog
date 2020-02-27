package com.teeya.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 调取授权服务器的接口
 * @Author: ZJH
 * @Date: 2020/1/16 14:18
 */

@Component
@FeignClient(name = "authorization-server", fallback = AuthorizationProviderFallback.class)
public interface AuthorizationProvider {

    // authorization-server依赖内部框架接口  org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint
    /**
     *  验证并解析token
     * @param value
     * @return
     */
    @RequestMapping(value = "/oauth/check_token")
    Map<String, ?> checkToken(@RequestParam("token") String value);

}
