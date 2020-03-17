package com.teeya.gateway.feign;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: ZJH
 * @Date: 2020/1/16 14:27
 */

@Component
@Slf4j
public class AuthenticationProviderFallback implements AuthenticationProvider {

    @Override
    public boolean hasPermission(String token, String url, String method) {
        log.info("进入鉴权判断降级====");
        return false;
    }

    @Override
    public String test() {
        return "降级";
    }
}
