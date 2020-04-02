package com.teeya.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.user.entity.form.UserForm;
import com.teeya.user.entity.form.UserUpdateForm;
import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.mapper.UserMapper;
import com.teeya.user.mapper.UserRoleRelationMapper;
import com.teeya.user.service.UserRoleRelationService;
import com.teeya.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
    public int insert(UserForm userForm) {
        UserEntity userEntity = BeanUtils.instantiateClass(UserEntity.class);
        BeanUtils.copyProperties(userForm, userEntity);
        if (StringUtils.isNotBlank(userEntity.getPassword())){
            userEntity.setPassword(passwordEncoder().encode(userEntity.getPassword()));
        }
        int insert = userMapper.insert(userEntity);
        log.info("insert_userEntity=======: " + userEntity.toString());
        userRoleRelationService.saveBatch(userEntity.getId(), userForm.getRoleIds());
        return insert;
    }

    @Override
    public void update(String id, UserUpdateForm userUpdateForm) {
        UserEntity userEntity = userMapper.selectById(id);
        BeanUtils.copyProperties(userUpdateForm, userEntity);
        if (StringUtils.isNotBlank(userUpdateForm.getPassword())){
            userUpdateForm.setPassword(passwordEncoder().encode(userUpdateForm.getPassword()));
        }
        userRoleRelationService.removeByUserId(id);
        userMapper.updateById(userEntity);
    }

    @Override
    public UserEntity queryById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public UserEntity queryByUsername(String username) {
        /*UserEntity userEntity = userEntityMapper.queryByUsername(username);
        Set<String> roleIds = null;
        if (null != userEntity) {
            Set<UserRoleRelationEntity> roleRelationEntities = userRoleRelationEntityMapper.queryListByUserId(userEntity.getId());
            roleIds = roleRelationEntities.stream().map(e -> e.getRoleId()).collect(Collectors.toSet());
        }
        UserVo userVo = BeanConverter.copy(userEntity, UserVo.class);
        userVo.setRoleIds(roleIds);*/
        return userMapper.queryByUsername(username);
    }

    @Override
    public UserEntity queryByPhone(String phone) {
        return userMapper.queryByPhone(phone);
    }

    @Override
    public int delete(String id) {
        return userMapper.deleteById(id);
    }
}
