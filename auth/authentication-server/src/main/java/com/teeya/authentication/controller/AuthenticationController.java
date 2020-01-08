package com.teeya.authentication.controller;

import com.teeya.authentication.service.ResourceService;
import com.teeya.user.entity.pojo.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZJH
 * @Date: 2020/1/8 17:39
 */

@RestController
@RequestMapping("/auth")
@Api(value = "auth", tags = {"鉴权接口"})
@Slf4j
public class AuthenticationController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "鉴权，判断用户是否有权限", notes = "获取指定用户信息")
    @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "string")
    @GetMapping("/queryByUsername")
    public UserEntity queryByUsername(@RequestParam(value = "username") String username) {
        return null;
        //return userService.queryByUsername(username);
    }
}
