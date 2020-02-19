package com.teeya.authorization.service;

import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.UserEntity;

import java.util.List;

public interface AuthorizationService {

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

    /**
     * 根据用户id获取相应的角色集合
     * @param userId
     * @return
     */
    List<RoleEntity> queryListByUserId(String userId);
}
