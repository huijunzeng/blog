package com.teeya.authorization.service.impl;

import com.teeya.authorization.feign.AuthorizationProvider;
import com.teeya.authorization.service.AuthorizationService;
import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private AuthorizationProvider authorizationProvider;

    @Override
    public UserEntity getByUniqueId(String uniqueId) {
        return authorizationProvider.getByUniqueId(uniqueId);
    }

    @Override
    public List<RoleEntity> queryListByUserId(String userId) {
        return authorizationProvider.queryListByUserId(userId);
    }
}
