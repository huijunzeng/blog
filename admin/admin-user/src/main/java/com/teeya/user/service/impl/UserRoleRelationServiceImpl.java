package com.teeya.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.user.entity.pojo.UserRoleRelationEntity;
import com.teeya.user.mapper.UserMapper;
import com.teeya.user.mapper.UserRoleRelationMapper;
import com.teeya.user.service.UserRoleRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelationEntity> implements UserRoleRelationService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

}
