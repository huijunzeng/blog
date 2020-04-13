package com.teeya.authentication.service;

import com.teeya.authentication.entity.ResourceEntity;

import java.util.List;

public interface AuthenticationService {

    /**
     * 根据用户名获取相应的角色集合
     * @param username
     * @return
     */
    List<ResourceEntity> queryListByUsername(String username);

    /**
     * 判断是由拥有访问权限
     * @param url
     * @param method
     * @return
     */
    boolean hasPermission(String url, String method);
}
