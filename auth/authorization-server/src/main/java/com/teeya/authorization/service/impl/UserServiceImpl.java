package com.teeya.authorization.service.impl;

import com.teeya.authorization.feign.UserProvider;
import com.teeya.authorization.service.UserService;
import com.teeya.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserProvider userProvider;

    @Override
    public UserEntity selectByUsername(String username) {
        return userProvider.selectByUsername(username);
    }
}
