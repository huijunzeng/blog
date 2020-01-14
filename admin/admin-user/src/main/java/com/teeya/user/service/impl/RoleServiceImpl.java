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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleEntityMapper roleEntityMapper;
    @Autowired
    private UserRoleRelationEntityMapper userRoleRelationEntityMapper;

    @Override
    public void insert(RoleForm roleForm) throws Exception {
        RoleEntity roleEntity = BeanConverter.copy(roleForm, RoleEntity.class);
        roleEntity.setId(String.valueOf(IdGenerate.getInstance().nextId()));
        roleEntityMapper.insert(roleEntity);
    }

    @Override
    public List<RoleEntity> queryListByUserId(String userId) {
        List<UserRoleRelationEntity> roleRelationEntities = userRoleRelationEntityMapper.queryListByUserId(userId);
        List<RoleEntity> roleEntities = new ArrayList<>();
        for (UserRoleRelationEntity userRoleRelationEntity : roleRelationEntities
             ) {
            RoleEntity roleEntity = roleEntityMapper.selectById(userRoleRelationEntity.getRoleId());
            System.out.println("roleEntity: " + roleEntity.toString());
            if (null != roleEntity) {
                roleEntities.add(roleEntity);
            }
        }
        return roleEntities;
    }

}
