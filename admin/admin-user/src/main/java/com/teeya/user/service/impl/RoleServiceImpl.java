package com.teeya.user.service.impl;

import com.teeya.common.util.BeanConverter;
import com.teeya.common.util.IdGenerate;
import com.teeya.user.entity.form.RoleForm;
import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.UserRoleRelationEntity;
import com.teeya.user.mapper.RoleEntityMapper;
import com.teeya.user.mapper.UserRoleRelationEntityMapper;
import com.teeya.user.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleEntityMapper roleEntityMapper;
    @Autowired
    private UserRoleRelationEntityMapper userRoleRelationEntityMapper;

    @Override
    public int insert(RoleForm roleForm) {
        RoleEntity roleEntity = BeanConverter.copy(roleForm, RoleEntity.class);
        //roleEntity.setId(String.valueOf(IdGenerate.getInstance().nextId()));
        return roleEntityMapper.insert(roleEntity);
    }

    @Override
    public List<RoleEntity> queryListByUserId(String userId) {
        List<UserRoleRelationEntity> userRoleRelationEntities = userRoleRelationEntityMapper.queryListByUserId(userId);
        Set<String> roleIds = userRoleRelationEntities.stream().map(userRoleRelationEntity -> userRoleRelationEntity.getRoleId()).collect(Collectors.toSet());
        return roleEntityMapper.selectBatchIds(roleIds);
    }

}
