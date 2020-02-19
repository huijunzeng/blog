package com.teeya.client.feign;


/**
 * @Author: ZJH
 * @Date: 2020/1/16 14:27
 */
public class AuthProviderFallback implements AuthProvider {

    @Override
    public boolean hasPermission(/*String authentication, */String url, String method) {
        return false;
    }
}
