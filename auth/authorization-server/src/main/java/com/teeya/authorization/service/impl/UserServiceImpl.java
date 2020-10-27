package com.teeya.authorization.service.impl;

import com.teeya.authorization.entity.UserEntity;
import com.teeya.authorization.feign.AuthorizationProvider;
import com.teeya.authorization.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthorizationProvider authorizationProvider;

    @Override
    public UserEntity getByUniqueId(String uniqueId) {
        return authorizationProvider.getByUniqueId(uniqueId).getData();
    }

}
