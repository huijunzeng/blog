package com.teeya.authorization.feign;

import com.teeya.common.entity.vo.Result;
import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.UserEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ZJH
 * @Date: 2020/1/8 9:48
 */

@Component
public class AuthorizationProviderFallback implements FallbackFactory<AuthorizationProvider> {

    @Override
    public AuthorizationProvider create(Throwable throwable) {
        return new AuthorizationProvider() {
            @Override
            public Result<UserEntity> queryByUsername(String username) {
                System.out.println("===============降级");
                return null;
            }

            @Override
            public Result<UserEntity> loadUserByPhone(String phone) {
                return null;
            }

            @Override
            public Result<UserEntity> queryListByUserId(String userId) {
                return null;
            }
        };
    }
}
