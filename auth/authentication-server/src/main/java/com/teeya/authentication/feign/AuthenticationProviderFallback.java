package com.teeya.authentication.feign;

import com.teeya.authentication.entity.ResourceEntity;
import com.teeya.common.core.entity.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ZJH
 * @Date: 2020/1/8 9:48
 */

@Component
@Slf4j
public class AuthenticationProviderFallback implements AuthenticationProvider {

    @Override
    public R<List<ResourceEntity>> getAll() {
        return R.fail("降级");
    }

    @Override
    public R<List<ResourceEntity>> queryListByUsername(String username) {
        log.info("=================触发降级");
        return R.fail("降级");
    }
}
