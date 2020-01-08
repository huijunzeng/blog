package com.teeya.authentication.feign;

import com.teeya.user.entity.pojo.ResourceEntity;
import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ZJH
 * @Date: 2020/1/8 9:48
 */

@Component
public class UserProviderFallback implements UserProvider {

    @Override
    public List<ResourceEntity> queryResourceListByUsername(String username) {
        System.out.println("=================触发降级");
        return null;
    }
}
