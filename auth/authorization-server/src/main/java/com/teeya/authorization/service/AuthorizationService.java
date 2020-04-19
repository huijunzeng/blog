package com.teeya.authorization.service;

import com.teeya.authorization.entity.UserEntity;
import com.teeya.authorization.entity.RoleEntity;

import java.util.List;

public interface AuthorizationService {

    /**
     * 根据用户名/用户手机号码（唯一标识）获取指定用户信息
     * @param uniqueId
     * @return
     */
    UserEntity getByUniqueId(String uniqueId);

    /**
     * 根据用户名获取相应的角色集合
     * @param username
     * @return
     */
    List<RoleEntity> queryListByUsername(String username);
}
