package com.teeya.gateway.service;

import com.teeya.gateway.feign.AuthProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ZJH
 * @Date: 2020/1/16 14:35
 */

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthProvider authProvider;

    /**
     * 不需要网关签权的url配置(/oauth,/open)
     * 默认/oauth开头是不需要的
     */
    //@Value("${gateway.ignore.authentication.url}")
    private List<String> ignoreUrls = null;

    @Override
    public boolean isIgnoreAuthenticationUrl(String url) {
        log.info("ignoreUrls:{}", ignoreUrls);
        return ignoreUrls.stream().anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }

    @Override
    public boolean hasPermission(String url, String method) {
        return authProvider.hasPermission(url, method);
    }
}
