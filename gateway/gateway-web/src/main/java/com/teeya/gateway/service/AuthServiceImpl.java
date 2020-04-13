package com.teeya.gateway.service;

import com.teeya.gateway.feign.AuthorizationProvider;
import com.teeya.gateway.feign.AuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @Author: ZJH
 * @Date: 2020/1/16 14:35
 */

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private AuthorizationProvider authorizationProvider;

    /**
     * 不需要网关签权的url配置(/oauth,/open)
     * 默认/oauth开头是不需要的
     */
    @Value("${gateway.ignore.authentication.url}")
    private String ignoreUrls = "/oauth/";
    //private List<String> ignoreUrls = null;

    @Override
    public boolean isIgnoreAuthenticationUrl(String url) {
        log.info("ignoreUrls:{}", ignoreUrls);
        //return ignoreUrls.stream().anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
        return Stream.of(this.ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }

    @Override
    public boolean hasPermission(String token, String url, String method) {
        log.info("进入鉴权判断222");
        return authenticationProvider.hasPermission(token, url, method);
    }

    @Override
    public Map<String, ?> checkToken(String token) {
        return authorizationProvider.checkToken(token);
    }

    @Override
    public String hello() {
        return authenticationProvider.test();
    }
}
