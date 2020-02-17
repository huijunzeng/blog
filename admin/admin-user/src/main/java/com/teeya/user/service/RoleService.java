package com.teeya.user.service;

import com.teeya.user.entity.form.RoleForm;
import com.teeya.user.entity.pojo.RoleEntity;

import java.util.List;

public interface RoleService {

    /**
     * 新增角色
     * @param roleForm
     * @throws Exception
     */
    int insert(RoleForm roleForm);

    /**
     * 根据用户id获取相应的角色集合
     * @param userId
     * @return
     */
    List<RoleEntity> queryListByUserId(String userId);
}
