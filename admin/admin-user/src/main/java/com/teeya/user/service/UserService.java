package com.teeya.user.service;

import com.teeya.user.entity.pojo.UserEntity;

public interface UserService {

    /**
     * 根据用户名获取指定用户信息
     * @param username
     * @return
     */
    UserEntity queryByUsername(String username);

    /**
     * 根据用户手机号码获取指定用户信息
     * @param phone
     * @return
     */
    UserEntity selectByPhone(String phone);

    /**
     * 根据用户id删除用户
     * @param id
     */
    int delete(String id);
}
