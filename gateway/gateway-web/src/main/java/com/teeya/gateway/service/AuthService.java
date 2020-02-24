package com.teeya.gateway.service;

/**
 * @Author: ZJH
 * @Date: 2020/1/16 14:34
 */
public interface AuthService {

    /**
     * 判断是否为不需鉴权的url  是则返回true
     * @param url
     * @return
     */
    boolean isIgnoreAuthenticationUrl(String url);

    /**
     * 判断是否为不需鉴权的url  是则返回true
     * @param url
     * @return
     */
    boolean hasPermission(String token, String url, String method);
}
