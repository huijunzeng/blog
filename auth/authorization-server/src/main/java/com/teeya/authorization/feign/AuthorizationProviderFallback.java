package com.teeya.authorization.feign;

import com.teeya.authorization.entity.UserEntity;
import com.teeya.authorization.entity.RoleEntity;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ZJH
 * @Date: 2020/1/8 9:48
 */

@Component
@Slf4j
public class AuthorizationProviderFallback implements FallbackFactory<AuthorizationProvider> {

    @Override
    public AuthorizationProvider create(Throwable throwable) {
        return new AuthorizationProvider() {
            @Override
            public UserEntity getByUniqueId(String uniqueId) {
                log.info("===============降级");
                return null;
            }

            @Override
            public List<RoleEntity> queryListByUsername(String username) {
                return null;
            }
        };
    }
}
