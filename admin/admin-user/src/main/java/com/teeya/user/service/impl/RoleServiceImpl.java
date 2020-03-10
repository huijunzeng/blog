package com.teeya.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.common.util.BeanConverter;
import com.teeya.user.entity.form.RoleForm;
import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.UserRoleRelationEntity;
import com.teeya.user.mapper.RoleMapper;
import com.teeya.user.mapper.UserRoleRelationMapper;
import com.teeya.user.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public int insert(RoleForm roleForm) {
        RoleEntity roleEntity = BeanConverter.copy(roleForm, RoleEntity.class);
        //roleEntity.setId(String.valueOf(IdGenerate.getInstance().nextId()));
        return roleMapper.insert(roleEntity);
    }

    @Override
    public List<RoleEntity> queryListByUserId(String userId) {
        List<UserRoleRelationEntity> userRoleRelationEntities = userRoleRelationMapper.queryListByUserId(userId);
        Set<String> roleIds = userRoleRelationEntities.stream().map(userRoleRelationEntity -> userRoleRelationEntity.getRoleId()).collect(Collectors.toSet());
        return roleMapper.selectBatchIds(roleIds);
    }

}
