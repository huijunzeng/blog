package com.teeya.gateway.feign;


import com.teeya.common.core.entity.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: ZJH
 * @Date: 2020/1/16 14:27
 */

@Component
@Slf4j
public class AuthenticationProviderFallback implements AuthenticationProvider {

    @Override
    public R<Boolean> hasPermission(String token, String url, String method) {
        log.info("进入鉴权判断降级====");
        return R.status(false);
    }

    @Override
    public R<String> test() {
        return R.fail("降级");
    }
}
