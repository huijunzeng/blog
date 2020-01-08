package com.teeya.user.service;

import com.teeya.user.entity.form.ResourceForm;
import com.teeya.user.entity.pojo.ResourceEntity;

import java.util.List;
import java.util.Set;

public interface ResourceService {

    /**
     * 新增资源
     * @param resourceForm
     */
    void insert(ResourceForm resourceForm) throws Exception;

    /**
     * 根据资源id集合获取相应的资源集合
     * @param resourceIds
     */
    List<ResourceEntity> queryListByResourceIds(Set<String> resourceIds);

    /**
     * 根据用户id获取相应的资源集合
     * @param userId
     * @return
     */
    List<ResourceEntity> queryListByUserId(String userId);

    /**
     * 根据用户名获取相应的资源集合
     * @param username
     * @return
     */
    List<ResourceEntity> queryListByUsername(String username);
}
