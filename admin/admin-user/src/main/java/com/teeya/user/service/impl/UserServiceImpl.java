package com.teeya.user.service.impl;

import com.teeya.common.util.BeanConverter;
import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.entity.pojo.UserRoleRelationEntity;
import com.teeya.user.entity.vo.UserVo;
import com.teeya.user.mapper.UserEntityMapper;
import com.teeya.user.mapper.UserRoleRelationEntityMapper;
import com.teeya.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;
    @Autowired
    private UserRoleRelationEntityMapper userRoleRelationEntityMapper;

    public UserVo queryByUsername(String username) {
        UserEntity userEntity = userEntityMapper.queryByUsername(username);
        Set<String> roleIds = null;
        if (null != userEntity) {
            Set<UserRoleRelationEntity> roleRelationEntities = userRoleRelationEntityMapper.queryListByUserId(userEntity.getId());
            roleIds = roleRelationEntities.stream().map(e -> e.getRoleId()).collect(Collectors.toSet());
        }
        UserVo userVo = BeanConverter.copy(userEntity, UserVo.class);
        userVo.setRoleIds(roleIds);
        return userVo;
    }

    @Override
    public UserEntity selectByPhone(String phone) {
        return userEntityMapper.selectByPhone(phone);
    }
}
