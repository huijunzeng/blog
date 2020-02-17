package com.teeya.authorization.service.impl;

import com.teeya.authorization.feign.AuthorizationProvider;
import com.teeya.authorization.service.UserService;
import com.teeya.common.entity.vo.Result;
import com.teeya.user.entity.pojo.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthorizationProvider authorizationProvider;

    @Override
    public Result queryByUsername(String username) {
        return authorizationProvider.queryByUsername(username);
    }

    @Override
    public Result loadUserByPhone(String phone) {
        return authorizationProvider.loadUserByPhone(phone);
    }
}
