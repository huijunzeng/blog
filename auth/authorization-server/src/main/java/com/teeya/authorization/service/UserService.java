package com.teeya.authorization.service;

import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.entity.vo.UserVo;

public interface UserService {

    /**
     * 根据username查找用户信息
     * @param username
     * @return
     */
    UserEntity queryByUsername(String username);

    /**
     * 根据phone查找用户信息
     * @param phone
     * @return
     */
    UserEntity loadUserByPhone(String phone);
}
