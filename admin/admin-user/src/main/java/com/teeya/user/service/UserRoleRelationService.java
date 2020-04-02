package com.teeya.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.user.entity.pojo.UserRoleRelationEntity;

import java.util.Set;

public interface UserRoleRelationService extends IService<UserRoleRelationEntity> {

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveBatch(String userId, Set<String> roleIds);

    /**
     * 根据用户id删除用户的角色
     * @param userId
     * @return
     */
    boolean removeByUserId(String userId);
}
