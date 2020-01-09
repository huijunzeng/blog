package com.teeya.user.service.impl;

import com.teeya.user.mapper.UserEntityMapper;
import com.teeya.user.mapper.UserRoleRelationEntityMapper;
import com.teeya.user.service.UserRoleRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRoleRelationServiceImpl implements UserRoleRelationService {

    @Autowired
    private UserEntityMapper userEntityMapper;
    @Autowired
    private UserRoleRelationEntityMapper userRoleRelationEntityMapper;

}
