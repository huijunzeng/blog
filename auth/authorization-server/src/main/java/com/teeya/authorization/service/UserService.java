package com.teeya.authorization.service;


import com.teeya.authorization.entity.UserEntity;

public interface UserService {

    /**
     * 根据用户名/用户手机号码（唯一标识）获取指定用户信息
     * @param uniqueId
     * @return
     */
    UserEntity getByUniqueId(String uniqueId);

}
