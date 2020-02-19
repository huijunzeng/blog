package com.teeya.authentication.feign;

import com.teeya.user.entity.pojo.ResourceEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ZJH
 * @Date: 2020/1/8 9:48
 */

@Component
public class AuthenticationProviderFallback implements AuthenticationProvider {

    @Override
    public List<ResourceEntity> queryAll() {
        return null;
    }

    @Override
    public List<ResourceEntity> queryListByUsername(String username) {
        System.out.println("=================触发降级");
        return null;
    }
}
