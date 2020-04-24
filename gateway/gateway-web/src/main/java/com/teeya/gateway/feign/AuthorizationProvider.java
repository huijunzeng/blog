package com.teeya.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
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
     *
     *  {
     *     "aud":[
     *         "blog"
     *     ],
     *     "user_name":"admin",
     *     "scope":[
     *         "read"
     *     ],
     *     "roles":[
     *         "R001"
     *     ],
     *     "active":true,
     *     "exp":1587786579,
     *     "authorities":[
     *         "R001"
     *     ],
     *     "jti":"42d6093b-204a-4b70-9f5f-adfc40e384d5",
     *     "client_id":"test_client"
     * }
     * @param value
     * @return
     */
    @RequestMapping(value = "/oauth/check_token")
    Map<String, ?> checkToken(@RequestParam("token") String value);

}
