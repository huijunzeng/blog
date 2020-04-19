package com.teeya.authorization.service;

import com.teeya.authorization.entity.RoleEntity;

import java.util.List;

public interface RoleService {

    /**
     * 根据用户名获取相应的角色集合
     * @param username
     * @return
     */
    List<RoleEntity> queryListByUsername(String username);
}
