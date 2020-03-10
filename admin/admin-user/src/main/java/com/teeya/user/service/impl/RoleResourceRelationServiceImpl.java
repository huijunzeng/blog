package com.teeya.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.user.entity.pojo.RoleResourceRelationEntity;
import com.teeya.user.mapper.RoleResourceRelationMapper;
import com.teeya.user.service.RoleResourceRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@Slf4j
public class RoleResourceRelationServiceImpl extends ServiceImpl<RoleResourceRelationMapper, RoleResourceRelationEntity> implements RoleResourceRelationService {

    private RoleResourceRelationMapper roleResourceRelationMapper;

    @Override
    public List<RoleResourceRelationEntity> queryListByRoleIds(Set<String> roleIds) {
        return roleResourceRelationMapper.queryListByRoleIds(roleIds);
    }
}
