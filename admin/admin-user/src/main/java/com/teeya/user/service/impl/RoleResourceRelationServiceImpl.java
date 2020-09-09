package com.teeya.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.user.entity.pojo.RoleResourceRelationEntity;
import com.teeya.user.mapper.RoleResourceRelationMapper;
import com.teeya.user.service.RoleResourceRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@Slf4j
public class RoleResourceRelationServiceImpl extends ServiceImpl<RoleResourceRelationMapper, RoleResourceRelationEntity> implements RoleResourceRelationService {

    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;

    @Override
    public List<RoleResourceRelationEntity> queryListByRoleIds(Set<Long> roleIds) {
        QueryWrapper<RoleResourceRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(RoleResourceRelationEntity::getRoleId, roleIds);
        return super.list(queryWrapper);
    }
}
