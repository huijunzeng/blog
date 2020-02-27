package com.teeya.gateway.feign;


import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: ZJH
 * @Date: 2020/1/16 14:27
 */

@Component
public class AuthorizationProviderFallback implements AuthorizationProvider {

    @Override
    public Map<String, ?> checkToken(String value) {
        System.out.println("checkToken降级====");
        return null;
    }
}
