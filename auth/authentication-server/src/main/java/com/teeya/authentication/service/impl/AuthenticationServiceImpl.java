package com.teeya.authentication.service.impl;

import com.teeya.authentication.feign.AuthenticationProvider;
import com.teeya.authentication.service.AuthenticationService;
import com.teeya.user.entity.pojo.ResourceEntity;
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
        return authenticationProvider.queryListByUsername(username);
    }

    @Override
    public boolean hasPermission(String url, String method) {
        // 在登录成功后，Spring Security创建Authentication对象并帮我们放到了SecurityContextHolder上下文中
        // 通过Authentication对象可以获取到用户信息
        log.info("url以及method: " + url + "===============" + method);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication信息: " + authentication.toString());
        // todo
        //List<ResourceEntity> resourceEntities = this.queryListByUsername(authentication.getName());
        //return this.isMatch(url, resourceEntities);
        return true;
    }

    private boolean isMatch(String url, List<ResourceEntity> resourceEntities) {
        return resourceEntities.stream().anyMatch(resourceEntity -> resourceEntity.getUrl().equals(url));
    }
}
