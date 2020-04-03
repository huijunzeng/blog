package com.teeya.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.user.entity.form.RoleForm;
import com.teeya.user.entity.form.RoleQueryForm;
import com.teeya.user.entity.form.RoleUpdateForm;
import com.teeya.user.entity.pojo.RoleEntity;

import java.util.List;

public interface RoleService extends IService<RoleEntity> {

    /**
     * 新增角色
     * @param roleForm
     * @throws Exception
     */
    boolean insert(RoleForm roleForm);

    /**
     * 更新指定角色信息
     * @param id
     * @param roleUpdateForm
     */
    boolean update(String id, RoleUpdateForm roleUpdateForm);

    /**
     * 根据角色id获取指定角色信息
     * @param id
     * @return
     */
    RoleEntity getById(String id);

    /**
     * 根据用户id获取相应的角色集合
     * @param userId
     * @return
     */
    List<RoleEntity> queryListByUserId(String userId);

    /**
     * 根据条件获取角色信息列表
     * @param roleQueryForm
     * @return
     */
    IPage queryList(RoleQueryForm roleQueryForm);

    /**
     * 获取所有角色信息列表
     * @return
     */
    List<RoleEntity> getAll();
}
