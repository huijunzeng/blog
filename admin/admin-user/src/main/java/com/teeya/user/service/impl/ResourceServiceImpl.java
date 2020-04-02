package com.teeya.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.user.entity.form.ResourceForm;
import com.teeya.user.entity.pojo.ResourceEntity;
import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.RoleResourceRelationEntity;
import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.mapper.ResourceMapper;
import com.teeya.user.mapper.RoleMapper;
import com.teeya.user.mapper.RoleResourceRelationMapper;
import com.teeya.user.mapper.UserRoleRelationMapper;
import com.teeya.user.service.ResourceService;
import com.teeya.user.service.RoleResourceRelationService;
import com.teeya.user.service.RoleService;
import com.teeya.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
    public int insert(ResourceForm resourceForm) {
        ResourceEntity resourceEntity = BeanUtils.instantiateClass(ResourceEntity.class);
        BeanUtils.copyProperties(resourceForm, resourceEntity);
        return resourceMapper.insert(resourceEntity);
    }

    @Override
    public List<ResourceEntity> queryListByResourceIds(Set<String> resourceIds) {
        return resourceMapper.selectBatchIds(resourceIds);
    }

    @Override
    public List<ResourceEntity> queryListByUserId(String userId) {
        List<RoleEntity> roleEntities = roleService.queryListByUserId(userId);
        Set<String> roleIds = roleEntities.stream().map(roleEntity -> roleEntity.getId()).collect(Collectors.toSet());
        //List<RoleResourceRelationEntity> roleResourceRelationEntities = roleResourceRelationMapper.selectBatchIds(roleIds);
        List<RoleResourceRelationEntity> roleResourceRelationEntities = roleResourceRelationService.queryListByRoleIds(roleIds);
        System.out.println("==============: " + roleResourceRelationEntities.size());
        Set<String> resourceIds = roleResourceRelationEntities.stream().map(roleResourceRelationEntity -> roleResourceRelationEntity.getResourceId()).collect(Collectors.toSet());
        System.out.println(555);
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
        return resourceMapper.selectList(null);
    }
}
