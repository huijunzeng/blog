package com.teeya.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.user.entity.pojo.RoleResourceRelationEntity;

import java.util.List;
import java.util.Set;

public interface RoleResourceRelationService extends IService<RoleResourceRelationEntity> {

    /**
     * 根据用户角色id获取相应的角色资源关联集合
     * @param roleIds
     * @return
     */
    List<RoleResourceRelationEntity> queryListByRoleIds(Set<Long> roleIds);
}
