package com.teeya.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.user.entity.form.UserForm;
import com.teeya.user.entity.form.UserQueryForm;
import com.teeya.user.entity.form.UserUpdateForm;
import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.mapper.UserMapper;
import com.teeya.user.service.UserRoleRelationService;
import com.teeya.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleRelationService userRoleRelationService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public boolean insert(UserForm userForm) {
        UserEntity userEntity = BeanUtils.instantiateClass(UserEntity.class);
        BeanUtils.copyProperties(userForm, userEntity);
        if (StringUtils.isNotBlank(userEntity.getPassword())){
            userEntity.setPassword(passwordEncoder().encode(userEntity.getPassword()));
        }
        super.save(userEntity);
        log.info("insert_userEntity=======: " + userEntity.toString());
        return userRoleRelationService.saveBatch(userEntity.getId(), userForm.getRoleIds());
    }

    @Override
    public boolean update(String id, UserUpdateForm userUpdateForm) {
        UserEntity userEntity = super.getById(id);
        BeanUtils.copyProperties(userUpdateForm, userEntity);
        if (StringUtils.isNotBlank(userUpdateForm.getPassword())){
            userUpdateForm.setPassword(passwordEncoder().encode(userUpdateForm.getPassword()));
        }
        userRoleRelationService.removeByUserId(id);
        super.updateById(userEntity);
        return userRoleRelationService.saveBatch(userEntity.getId(), userUpdateForm.getRoleIds());
    }

    @Override
    public UserEntity get(String id) {
        return super.getById(id);
    }

    @Override
    public UserEntity getByUniqueId(String uniqueId) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserEntity::getUsername, uniqueId).or().eq(UserEntity::getPhone, uniqueId).eq(UserEntity::getDeleted, 0);
        return super.getOne(queryWrapper);
    }

    @Override
    public IPage queryList(UserQueryForm userQueryForm) {
        Page page = userQueryForm.getPage();
        LambdaQueryWrapper<UserEntity> queryWrapper = userQueryForm.build().lambda();
        queryWrapper.eq(StringUtils.isNotBlank(userQueryForm.getUsername()), UserEntity::getPhone, userQueryForm.getUsername());
        queryWrapper.eq(StringUtils.isNotBlank(userQueryForm.getPhone()), UserEntity::getPhone, userQueryForm.getPhone());
        queryWrapper.orderByDesc(UserEntity::getUpdatedTime);
        IPage<UserEntity> iPageUser = super.page(page, queryWrapper);
        return iPageUser;
    }

    @Override
    public boolean delete(String id) {
        super.removeById(id);
        return userRoleRelationService.removeByUserId(id);
    }
}
