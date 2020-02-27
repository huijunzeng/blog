package com.teeya.gateway.service;

import java.util.Map;

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

    /**
     * 验证token并返回payload等相关内容
     * @param token
     * @return
     */
    Map<String, ?> checkToken(String token);

    String hello();
}
