package com.teeya.authentication.service.impl;

import com.teeya.authentication.feign.UserProvider;
import com.teeya.authentication.service.ResourceService;
import com.teeya.user.entity.pojo.ResourceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ZJH
 * @Date: 2020/1/8 16:44
 */

@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private UserProvider userProvider;

    @Override
    public List<ResourceEntity> queryListByUsername(String username) {
        return userProvider.queryListByUsername(username);
    }
}
