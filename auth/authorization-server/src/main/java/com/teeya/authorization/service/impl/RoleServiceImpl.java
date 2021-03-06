package com.teeya.authorization.service.impl;

import com.teeya.authorization.entity.RoleEntity;
import com.teeya.authorization.feign.AuthorizationProvider;
import com.teeya.authorization.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private AuthorizationProvider authorizationProvider;

    @Override
    public List<RoleEntity> queryListByUsername(String username) {
        return authorizationProvider.queryListByUsername(username);
    }
}
