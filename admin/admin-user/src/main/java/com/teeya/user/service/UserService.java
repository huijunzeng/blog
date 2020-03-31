package com.teeya.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.user.entity.form.UserForm;
import com.teeya.user.entity.pojo.UserEntity;

public interface UserService extends IService<UserEntity> {

    /**
     * 新增用户信息
     * @param userForm
     * @return
     */
    int insert(UserForm userForm);

    /**
     * 根据用户名获取指定用户信息
     * @param username
     * @return
     */
    UserEntity queryByUsername(String username);

    /**
     * 根据用户手机号码获取指定用户信息
     * @param phone
     * @return
     */
    UserEntity queryByPhone(String phone);

    /**
     * 根据用户id删除用户
     * @param id
     */
    int delete(String id);
}
