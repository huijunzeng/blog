package com.teeya.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.user.entity.form.RoleSaveForm;
import com.teeya.user.entity.form.RoleQueryForm;
import com.teeya.user.entity.form.RoleUpdateForm;
import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.entity.pojo.UserRoleRelationEntity;
import com.teeya.user.mapper.RoleMapper;
import com.teeya.user.mapper.UserRoleRelationMapper;
import com.teeya.user.service.RoleService;
import com.teeya.user.service.UserRoleRelationService;
import com.teeya.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleRelationService userRoleRelationService;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;
    @Autowired
    private UserService userService;

    @Override
    public boolean save(RoleSaveForm roleSaveForm) {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleEntity::getCode, roleSaveForm.getCode());
        if (null != this.getOne(queryWrapper)) {
            return false;
        }
        RoleEntity roleEntity = BeanUtils.instantiateClass(RoleEntity.class);
        BeanUtils.copyProperties(roleSaveForm, roleEntity);
        return super.save(roleEntity);
    }

    @Override
    public boolean update(String id, RoleUpdateForm roleUpdateForm) {
        RoleEntity roleEntity = super.getById(id);
        BeanUtils.copyProperties(roleUpdateForm, roleEntity);
        return super.updateById(roleEntity);
    }

    @Override
    public RoleEntity get(String id) {
        return super.getById(id);
    }

    @Override
    public List<RoleEntity> queryListByUserId(String userId) {
        QueryWrapper<UserRoleRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleRelationEntity::getUserId, userId);
        List<UserRoleRelationEntity> userRoleRelationEntities = userRoleRelationService.list(queryWrapper);
        Set<String> roleIds = userRoleRelationEntities.stream().map(userRoleRelationEntity -> userRoleRelationEntity.getRoleId()).collect(Collectors.toSet());
        return super.listByIds(roleIds);
    }

    @Override
    public List<RoleEntity> queryListByUsername(String username) {
        UserEntity userEntity = userService.getByUniqueId(username);
        Assert.notNull(userEntity, "user not found");
        QueryWrapper<UserRoleRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleRelationEntity::getUserId, userEntity.getId());
        List<UserRoleRelationEntity> userRoleRelationEntities = userRoleRelationService.list(queryWrapper);
        Set<String> roleIds = userRoleRelationEntities.stream().map(userRoleRelationEntity -> userRoleRelationEntity.getRoleId()).collect(Collectors.toSet());
        return super.listByIds(roleIds);
    }

    @Override
    public IPage queryList(RoleQueryForm roleQueryForm) {
        Page page = roleQueryForm.getPage();
        LambdaQueryWrapper<RoleEntity> queryWrapper = roleQueryForm.build().lambda();
        queryWrapper.eq(StringUtils.isNotBlank(roleQueryForm.getCode()), RoleEntity::getCode, roleQueryForm.getCode());
        queryWrapper.eq(StringUtils.isNotBlank(roleQueryForm.getName()), RoleEntity::getName, roleQueryForm.getName());
        queryWrapper.orderByDesc(RoleEntity::getUpdatedTime);
        IPage<RoleEntity> iPageRole = super.page(page, queryWrapper);
        return iPageRole;
    }

    @Override
    public List<RoleEntity> getAll() {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleEntity::getDeleted, 0).orderByDesc(RoleEntity::getUpdatedTime);
        return super.list(queryWrapper);
    }

    @Override
    public boolean remove(String id) {
        return super.removeById(id);
    }

}
