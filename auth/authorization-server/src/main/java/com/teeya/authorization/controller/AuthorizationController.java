package com.teeya.authorization.controller;

import com.teeya.authorization.service.AuthorizationService;
import com.teeya.common.core.entity.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ZJH
 * @Date: 2020/1/8 17:39
 */

@RestController
@RequestMapping("/")
@Api(value = "/", tags = {"授权接口"})
@Slf4j
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @ApiOperation(value = "用户登出", notes = "用户登出")
    @ApiImplicitParam(paramType = "path", name = "token", value = "token", required = true, dataTypeClass = String.class)
    @DeleteMapping("/oauth/token/{token}")
    public R<Boolean> logout(@PathVariable String token) {
        return R.success(authorizationService.logout(token));
    }
}
