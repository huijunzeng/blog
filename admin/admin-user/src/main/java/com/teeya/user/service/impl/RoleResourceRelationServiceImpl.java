package com.teeya.user.service.impl;

import com.teeya.user.entity.pojo.RoleResourceRelationEntity;
import com.teeya.user.mapper.RoleResourceRelationEntityMapper;
import com.teeya.user.service.RoleResourceRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@Slf4j
public class RoleResourceRelationServiceImpl implements RoleResourceRelationService {

    private RoleResourceRelationEntityMapper roleResourceRelationEntityMapper;

    @Override
    public List<RoleResourceRelationEntity> queryListByRoleIds(Set<String> roleIds) {
        return roleResourceRelationEntityMapper.queryListByRoleIds(roleIds);
    }
}
