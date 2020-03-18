package com.teeya.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.user.entity.form.UserForm;
import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.mapper.UserMapper;
import com.teeya.user.mapper.UserRoleRelationMapper;
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
    private UserRoleRelationMapper userRoleRelationyMapper;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public int insert(UserForm userForm) {
        UserEntity userEntity = BeanUtils.instantiateClass(UserEntity.class);
        BeanUtils.copyProperties(userForm, userEntity);
        if (StringUtils.isNotBlank(userEntity.getPassword()))
            userEntity.setPassword(passwordEncoder().encode(userEntity.getPassword()));
        log.info("insert_userEntity=======: " + userEntity.toString());
        return userMapper.insert(userEntity);
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
    public UserEntity selectByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    @Override
    public int delete(String id) {
        return userMapper.deleteById(id);
    }
}
