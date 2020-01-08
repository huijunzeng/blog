package com.teeya.authorization.service.impl;

import com.teeya.authorization.feign.UserProvider;
import com.teeya.authorization.service.UserService;
import com.teeya.user.entity.pojo.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserProvider userProvider;

    @Override
    public UserEntity queryByUsername(String username) {
        return userProvider.queryByUsername(username);
    }

    @Override
    public UserEntity loadUserByPhone(String phone) {
        return userProvider.loadUserByPhone(phone);
    }
}
