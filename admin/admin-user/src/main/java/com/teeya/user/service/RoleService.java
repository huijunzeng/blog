package com.teeya.user.service;

import com.teeya.user.entity.form.RoleForm;
import com.teeya.user.entity.pojo.RoleEntity;

import java.util.List;
import java.util.Set;

public interface RoleService {

    List<RoleEntity> queryListByUserId(String userId);

    void insert(RoleForm roleForm) throws Exception;
}
