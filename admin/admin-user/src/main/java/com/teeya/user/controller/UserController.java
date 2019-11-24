package com.teeya.user.controller;

import com.teeya.user.entity.UserEntity;
import com.teeya.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@Api("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户", notes = "获取指定用户信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "string")
    @GetMapping("/selectByUserName")
    public UserEntity selectByUserName(@RequestParam(value = "userName") String userName) {
        return userService.selectByUserName(userName);
    }


}
