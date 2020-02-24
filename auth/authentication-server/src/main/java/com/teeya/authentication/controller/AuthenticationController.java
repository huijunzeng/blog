package com.teeya.authentication.controller;

import com.teeya.authentication.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    private AuthenticationService authenticationService;

    @ApiOperation(value = "鉴权，判断用户是否有权限", notes = "获取指定用户的权限")
    @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "string")
    @PostMapping("/permission")
    public boolean hasPermission(@RequestParam String url, @RequestParam String method, HttpServletRequest request) {
        System.out.println("进入authentication-server鉴权判断");
        return authenticationService.hasPermission(url, method);
    }
}
