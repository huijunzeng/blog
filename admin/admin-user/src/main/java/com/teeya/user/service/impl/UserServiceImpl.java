package com.teeya.user.service.impl;

import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.mapper.UserEntityMapper;
import com.teeya.user.mapper.UserRoleRelationEntityMapper;
import com.teeya.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;
    @Autowired
    private UserRoleRelationEntityMapper userRoleRelationEntityMapper;

    public UserEntity queryByUsername(String username) {
        /*UserEntity userEntity = userEntityMapper.queryByUsername(username);
        Set<String> roleIds = null;
        if (null != userEntity) {
            Set<UserRoleRelationEntity> roleRelationEntities = userRoleRelationEntityMapper.queryListByUserId(userEntity.getId());
            roleIds = roleRelationEntities.stream().map(e -> e.getRoleId()).collect(Collectors.toSet());
        }
        UserVo userVo = BeanConverter.copy(userEntity, UserVo.class);
        userVo.setRoleIds(roleIds);*/
        return userEntityMapper.queryByUsername(username);
    }

    @Override
    public UserEntity selectByPhone(String phone) {
        return userEntityMapper.selectByPhone(phone);
    }

    @Override
    public int delete(String id) {
        return userEntityMapper.deleteById(id);
    }
}
