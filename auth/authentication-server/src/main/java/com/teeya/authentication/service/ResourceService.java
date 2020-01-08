package com.teeya.authentication.service;

import com.teeya.user.entity.pojo.ResourceEntity;

import java.util.List;

public interface ResourceService {

    /**
     * 根据用户名获取相应的角色集合
     * @param username
     * @return
     */
    List<ResourceEntity> queryResourceListByUsername(String username);
}
