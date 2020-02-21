package com.teeya.gateway.feign;


import org.springframework.stereotype.Component;

/**
 * @Author: ZJH
 * @Date: 2020/1/16 14:27
 */

@Component
public class AuthProviderFallback implements AuthProvider {

    @Override
    public boolean hasPermission(/*String authentication, */String url, String method) {
        return false;
    }
}
