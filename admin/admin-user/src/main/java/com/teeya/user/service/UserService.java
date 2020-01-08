package com.teeya.user.service;

import com.teeya.user.entity.pojo.UserEntity;

public interface UserService {

    UserEntity queryByUsername(String username);

    UserEntity selectByPhone(String phone);
}
