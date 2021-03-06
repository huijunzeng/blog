package com.teeya.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.user.entity.form.RoleSaveForm;
import com.teeya.user.entity.form.RoleQueryForm;
import com.teeya.user.entity.form.RoleUpdateForm;
import com.teeya.user.entity.pojo.RoleEntity;

import java.util.List;

public interface RoleService extends IService<RoleEntity> {

    /**
     * 新增角色
     * @param roleSaveForm
     * @throws Exception
     */
    boolean save(RoleSaveForm roleSaveForm);

    /**
     * 更新指定角色信息
     * @param id
     * @param roleUpdateForm
     */
    boolean update(Long id, RoleUpdateForm roleUpdateForm);

    /**
     * 根据角色id获取指定角色信息
     * @param id
     * @return
     */
    RoleEntity get(Long id);

    /**
     * 根据用户id获取相应的角色集合
     * @param userId
     * @return
     */
    List<RoleEntity> queryListByUserId(Long userId);

    /**
     * 根据用户名获取相应的角色集合
     * @param username
     * @return
     */
    List<RoleEntity> queryListByUsername(String username);

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

    /**
     * 根据id删除角色
     * @return
     */
    boolean remove(Long id);

}
