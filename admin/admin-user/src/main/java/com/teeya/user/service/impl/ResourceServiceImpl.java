package com.teeya.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.common.util.BeanConverter;
import com.teeya.common.util.IdGenerate;
import com.teeya.user.entity.form.ResourceForm;
import com.teeya.user.entity.pojo.ResourceEntity;
import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.RoleResourceRelationEntity;
import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.mapper.ResourceEntityMapper;
import com.teeya.user.mapper.RoleEntityMapper;
import com.teeya.user.mapper.RoleResourceRelationEntityMapper;
import com.teeya.user.mapper.UserRoleRelationEntityMapper;
import com.teeya.user.service.ResourceService;
import com.teeya.user.service.RoleResourceRelationService;
import com.teeya.user.service.RoleService;
import com.teeya.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<ResourceEntityMapper, ResourceEntity> implements ResourceService {

    @Autowired
    private ResourceEntityMapper resourceEntityMapper;
    @Autowired
    private RoleResourceRelationEntityMapper roleResourceRelationEntityMapper;
    @Autowired
    private RoleEntityMapper roleEntityMapper;
    @Autowired
    private UserRoleRelationEntityMapper userRoleRelationEntityMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleResourceRelationService roleResourceRelationService;

    @Override
    public int insert(ResourceForm resourceForm) {
        ResourceEntity resourceEntity = BeanConverter.copy(resourceForm, ResourceEntity.class);
        //resourceEntity.setId(String.valueOf(IdGenerate.getInstance().nextId()));
        return resourceEntityMapper.insert(resourceEntity);
    }

    @Override
    public List<ResourceEntity> queryListByResourceIds(Set<String> resourceIds) {
        return resourceEntityMapper.selectBatchIds(resourceIds);
    }

    @Override
    public List<ResourceEntity> queryListByUserId(String userId) {
        List<RoleEntity> roleEntities = roleService.queryListByUserId(userId);
        List<String> roleIds = roleEntities.stream().map(roleEntity -> roleEntity.getId()).collect(Collectors.toList());
        List<RoleResourceRelationEntity> roleResourceRelationEntities = roleResourceRelationEntityMapper.selectBatchIds(roleIds);
        //List<RoleResourceRelationEntity> roleResourceRelationEntities = roleResourceRelationService.queryListByRoleIds(roleIds);
        Set<String> resourceIds = roleResourceRelationEntities.stream().map(roleResourceRelationEntity -> roleResourceRelationEntity.getResourceId()).collect(Collectors.toSet());
        return this.queryListByResourceIds(resourceIds);
    }

    @Override
    public List<ResourceEntity> queryListByUsername(String username) {
        UserEntity userEntity = userService.queryByUsername(username);
        List<RoleEntity> roleEntities = roleService.queryListByUserId(userEntity.getId());
        Set<String> roleIds = roleEntities.stream().map(roleEntity -> roleEntity.getId()).collect(Collectors.toSet());
        List<RoleResourceRelationEntity> roleResourceRelationEntities = roleResourceRelationService.queryListByRoleIds(roleIds);
        Set<String> resourceIds = roleResourceRelationEntities.stream().map(roleResourceRelationEntity -> roleResourceRelationEntity.getResourceId()).collect(Collectors.toSet());
        return this.queryListByResourceIds(resourceIds);
    }

    @Override
    public List<ResourceEntity> queryAll() {
        return resourceEntityMapper.selectList(null);
    }
}
