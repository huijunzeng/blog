package com.teeya.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * 调取资源服务器的接口，进行鉴权
 * @Author: ZJH
 * @Date: 2020/1/16 14:18
 */

@Component
@FeignClient(name = "authentication-server", fallback = AuthenticationProviderFallback.class)
public interface AuthenticationProvider {

    @PostMapping(value = "/auth/permission")
    boolean hasPermission(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam("url") String url, @RequestParam("method") String method);

    @GetMapping(value = "/test/hello")
    String test();

}
