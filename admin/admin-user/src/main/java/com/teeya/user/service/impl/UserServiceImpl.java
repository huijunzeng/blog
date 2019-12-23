package com.teeya.user.service.impl;

import com.teeya.user.entity.UserEntity;
import com.teeya.user.mapper.UserEntityMapper;
import com.teeya.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    public UserEntity selectByUsername(String username) {
        return userEntityMapper.selectByUsername(username);
    }

    @Override
    public UserEntity selectByPhone(String phone) {
        return userEntityMapper.selectByPhone(phone);
    }
}
