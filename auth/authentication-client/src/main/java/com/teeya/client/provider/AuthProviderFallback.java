package com.teeya.client.provider;

import com.teeya.user.entity.pojo.ResourceEntity;

import java.util.List;

/**
 * @Author: ZJH
 * @Date: 2020/1/16 14:27
 */
public class AuthProviderFallback implements AuthProvider {
    @Override
    public List<ResourceEntity> queryAll() {
        return null;
    }
}
