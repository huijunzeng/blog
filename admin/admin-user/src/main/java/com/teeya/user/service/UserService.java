package com.teeya.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.user.entity.form.UserSaveForm;
import com.teeya.user.entity.form.UserQueryForm;
import com.teeya.user.entity.form.UserUpdateForm;
import com.teeya.user.entity.pojo.UserEntity;

public interface UserService extends IService<UserEntity> {

    /**
     * 新增用户信息
     * @param userSaveForm
     * @return
     */
    boolean save(UserSaveForm userSaveForm);

    /**
     * 更新指定用户信息
     * @param id
     * @param userUpdateForm
     */
    boolean update(Long id, UserUpdateForm userUpdateForm);

    /**
     * 根据用户id获取指定用户信息
     * @param id
     * @return
     */
    UserEntity get(Long id);

    /**
     * 根据用户名/用户手机号码（唯一标识）获取指定用户信息
     * @param uniqueId
     * @return
     */
    UserEntity getByUniqueId(String uniqueId);

    /**
     * 根据条件获取用户信息列表
     * @param userQueryForm
     * @return
     */
    IPage queryList(UserQueryForm userQueryForm);

    /**
     * 根据用户id删除用户
     * @param id
     */
    boolean remove(Long id);

}
