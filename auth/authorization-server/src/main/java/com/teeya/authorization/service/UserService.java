package com.teeya.authorization.service;

import com.teeya.user.entity.UserEntity;

public interface UserService {

    /**
     * 根据username查找用户信息
     * @param username
     * @return
     */
    UserEntity selectByUsername(String username);

    /**
     * 根据phone查找用户信息
     * @param phone
     * @return
     */
    UserEntity loadUserByPhone(String phone);
}
