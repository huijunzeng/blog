package com.teeya.gateway.feign;


import org.springframework.stereotype.Component;

/**
 * @Author: ZJH
 * @Date: 2020/1/16 14:27
 */

@Component
public class AuthProviderFallback implements AuthProvider {

    @Override
    public boolean hasPermission(String token, String url, String method) {
        System.out.println("进入鉴权判断降级====");
        return false;
    }

    @Override
    public String test() {
        return "降级";
    }
}
