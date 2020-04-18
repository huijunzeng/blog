package com.teeya.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.user.entity.form.ResourceSaveForm;
import com.teeya.user.entity.form.ResourceQueryForm;
import com.teeya.user.entity.form.UserUpdateForm;
import com.teeya.user.entity.pojo.*;
import com.teeya.user.mapper.ResourceMapper;
import com.teeya.user.mapper.RoleMapper;
import com.teeya.user.mapper.RoleResourceRelationMapper;
import com.teeya.user.mapper.UserRoleRelationMapper;
import com.teeya.user.service.ResourceService;
import com.teeya.user.service.RoleResourceRelationService;
import com.teeya.user.service.RoleService;
import com.teeya.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, ResourceEntity> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleResourceRelationService roleResourceRelationService;

    @Override
    public boolean save(ResourceSaveForm resourceSaveForm) {
        ResourceEntity resourceEntity = BeanUtils.instantiateClass(ResourceEntity.class);
        BeanUtils.copyProperties(resourceSaveForm, resourceEntity);
        return super.save(resourceEntity);
    }

    @Override
    public boolean update(String id, UserUpdateForm resourceUpdateForm) {
        ResourceEntity resourceEntity = super.getById(id);
        BeanUtils.copyProperties(resourceUpdateForm, resourceEntity);
        return super.updateById(resourceEntity);
    }

    @Override
    public ResourceEntity get(String id) {
        return super.getById(id);
    }

    @Override
    public List<ResourceEntity> queryListByResourceIds(Set<String> ids) {
        return super.listByIds(ids);
    }

    @Override
    public List<ResourceEntity> queryListByUserId(String userId) {
        List<RoleEntity> roleEntities = roleService.queryListByUserId(userId);
        Set<String> roleIds = roleEntities.stream().map(roleEntity -> roleEntity.getId()).collect(Collectors.toSet());
        List<RoleResourceRelationEntity> roleResourceRelationEntities = roleResourceRelationService.queryListByRoleIds(roleIds);
        Set<String> resourceIds = roleResourceRelationEntities.stream().map(roleResourceRelationEntity -> roleResourceRelationEntity.getResourceId()).collect(Collectors.toSet());
        return super.listByIds(resourceIds);
    }

    @Override
    public List<ResourceEntity> queryListByUsername(String username) {
        UserEntity userEntity = userService.getByUniqueId(username);
        List<RoleEntity> roleEntities = roleService.queryListByUserId(userEntity.getId());
        Set<String> roleIds = roleEntities.stream().map(roleEntity -> roleEntity.getId()).collect(Collectors.toSet());
        List<RoleResourceRelationEntity> roleResourceRelationEntities = roleResourceRelationService.queryListByRoleIds(roleIds);
        Set<String> resourceIds = roleResourceRelationEntities.stream().map(roleResourceRelationEntity -> roleResourceRelationEntity.getResourceId()).collect(Collectors.toSet());
        return super.listByIds(resourceIds);
    }

    @Override
    public List<ResourceEntity> queryListByRoleId(String roleId) {
        QueryWrapper<RoleResourceRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResourceRelationEntity::getRoleId, roleId);
        List<RoleResourceRelationEntity> roleResourceRelationEntities = roleResourceRelationService.list(queryWrapper);
        Set<String> resourceIds = roleResourceRelationEntities.stream().map(roleResourceRelationEntity -> roleResourceRelationEntity.getResourceId()).collect(Collectors.toSet());
        return super.listByIds(resourceIds);
    }

    @Override
    public IPage queryList(ResourceQueryForm resourceQueryForm) {
        Page page = resourceQueryForm.getPage();
        LambdaQueryWrapper<ResourceEntity> queryWrapper = resourceQueryForm.build().lambda();
        queryWrapper.eq(StringUtils.isNotBlank(resourceQueryForm.getType()), ResourceEntity::getType, resourceQueryForm.getType());
        queryWrapper.eq(StringUtils.isNotBlank(resourceQueryForm.getUrl()), ResourceEntity::getUrl, resourceQueryForm.getUrl());
        queryWrapper.eq(StringUtils.isNotBlank(resourceQueryForm.getName()), ResourceEntity::getName, resourceQueryForm.getName());
        queryWrapper.orderByDesc(ResourceEntity::getUpdatedTime);
        IPage<RoleEntity> iPageRole = super.page(page, queryWrapper);
        return iPageRole;
    }

    @Override
    public List<ResourceEntity> getAll() {
        QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ResourceEntity::getDeleted, 0).orderByDesc(ResourceEntity::getUpdatedTime);
        return super.list(queryWrapper);
    }

    @Override
    public boolean remove(String id) {
        return super.removeById(id);
    }
}
