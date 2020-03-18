package com.teeya.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.user.entity.form.ResourceForm;
import com.teeya.user.entity.pojo.ResourceEntity;

import java.util.List;
import java.util.Set;

public interface ResourceService extends IService<ResourceEntity> {

    /**
     * 新增资源
     * @param resourceForm
     */
    int insert(ResourceForm resourceForm);

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

    /**
     * 获取所有资源集合
     * @return
     */
    List<ResourceEntity> queryAll();
}
