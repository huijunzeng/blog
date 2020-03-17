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
public class AuthorizationProviderFallback implements AuthorizationProvider {

    @Override
    public Map<String, ?> checkToken(String value) {
        log.info("checkToken降级====");
        return null;
    }
}
