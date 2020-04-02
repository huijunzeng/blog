package com.teeya.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.user.entity.pojo.UserRoleRelationEntity;
import com.teeya.user.mapper.UserMapper;
import com.teeya.user.mapper.UserRoleRelationMapper;
import com.teeya.user.service.UserRoleRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelationEntity> implements UserRoleRelationService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public boolean saveBatch(String userId, Set<String> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)){
            return false;
        }
        this.removeByUserId(userId);
        Set<UserRoleRelationEntity> userRoles = roleIds.stream().map(roleId -> new UserRoleRelationEntity(userId, roleId)).collect(Collectors.toSet());
        return this.saveBatch(userRoles);
    }

    @Override
    public boolean removeByUserId(String userId) {
        /*UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("user_id", userId);
        updateWrapper.eq("deleted", 0);
        UserRoleRelationEntity userRoleRelationEntity = new UserRoleRelationEntity();
        userRoleRelationEntity.setDeleted(1);
        return userRoleRelationMapper.update(userRoleRelationEntity, updateWrapper);*/
        QueryWrapper<UserRoleRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleRelationEntity::getUserId, userId).eq(UserRoleRelationEntity::getDeleted, 0);
        return this.remove(queryWrapper);
    }
}
