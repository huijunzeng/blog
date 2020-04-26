package com.teeya.authorization.service.impl;

import com.teeya.authorization.entity.RoleEntity;
import com.teeya.authorization.entity.UserEntity;
import com.teeya.authorization.feign.AuthorizationProvider;
import com.teeya.authorization.service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private AuthorizationProvider authorizationProvider;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String ACCESS = "access:";
    private static final String AUTH_TO_ACCESS = "auth_to_access:";
    private static final String AUTH = "auth:";
    private static final String REFRESH_AUTH = "refresh_auth:";
    private static final String ACCESS_TO_REFRESH = "access_to_refresh:";
    private static final String REFRESH = "refresh:";
    private static final String REFRESH_TO_ACCESS = "refresh_to_access:";
    private static final String CLIENT_ID_TO_ACCESS = "client_id_to_access:";
    private static final String UNAME_TO_ACCESS = "uname_to_access:";

    @Override
    public UserEntity getByUniqueId(String uniqueId) {
        return authorizationProvider.getByUniqueId(uniqueId);
    }

    @Override
    public List<RoleEntity> queryListByUsername(String username) {
        return authorizationProvider.queryListByUsername(username);
    }

    @Override
    public boolean logout(String token) {
        // 删除相关的key
        boolean flag = stringRedisTemplate.delete(ACCESS + token) && stringRedisTemplate.delete(AUTH + token);
        return flag;
    }
}
