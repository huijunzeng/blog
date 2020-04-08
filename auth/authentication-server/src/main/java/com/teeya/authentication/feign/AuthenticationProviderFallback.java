package com.teeya.authentication.feign;

import com.teeya.user.entity.pojo.ResourceEntity;
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
    public List<ResourceEntity> getAll() {
        return null;
    }

    @Override
    public List<ResourceEntity> queryListByUsername(String username) {
        log.info("=================触发降级");
        return null;
    }
}
