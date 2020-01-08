package com.teeya.authorization.service.impl;

import com.teeya.authorization.feign.UserProvider;
import com.teeya.authorization.service.RoleService;
import com.teeya.authorization.service.UserService;
import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserProvider userProvider;

    @Override
    public List<RoleEntity> queryListByUserId(String userId) {
        return userProvider.queryListByUserId(userId);
    }
}
