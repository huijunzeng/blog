package com.teeya.authentication.service.impl;

import com.teeya.authentication.entity.ResourceEntity;
import com.teeya.authentication.feign.AuthenticationProvider;
import com.teeya.authentication.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ZJH
 * @Date: 2020/1/8 16:44
 */

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    public List<ResourceEntity> queryListByUsername(String username) {
        return authenticationProvider.queryListByUsername(username).getData();
    }

    @Override
    public boolean hasPermission(String url, String method) {
        // 在登录成功后，Spring Security创建Authentication对象并帮我们放到了SecurityContextHolder上下文中
        // 在请求头添加Authorization的token参数，然后通过SecurityContextHolder获取到Authentication对象的用户信息
        log.info("url以及method:{}, {}", url, method);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication信息:{}", authentication.toString());
        // 判断用户是否拥有该请求方法的权限
        List<ResourceEntity> resourceEntities = this.queryListByUsername(authentication.getName());
        return this.isMatch(url, method, resourceEntities);
    }

    private boolean isMatch(String url, String method, List<ResourceEntity> resourceEntities) {
        return resourceEntities.stream().anyMatch(resourceEntity -> resourceEntity.getUrl().equals(url) && resourceEntity.getMethod().equals(method));
    }
}
