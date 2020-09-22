package com.teeya.authentication.controller;

import com.teeya.authentication.service.AuthenticationService;
import com.teeya.common.core.entity.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParams ({
        @ApiImplicitParam(paramType = "query", name = "request", value = "request请求体", required = true, dataTypeClass = HttpServletRequest.class),
        @ApiImplicitParam(paramType = "query", name = "url", value = "url", required = true, dataTypeClass = String.class),
        @ApiImplicitParam(paramType = "query", name = "method", value = "method方法名", required = true, dataTypeClass = String.class)
    })
    @PostMapping("/permission")
    public R<Boolean> hasPermission(HttpServletRequest request, @RequestParam("url") String url, @RequestParam("method") String method) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("进入authentication-server鉴权判断: " + token);
        boolean b = authenticationService.hasPermission(url, method);
        return R.success(b);
    }

    @GetMapping("/hello")
    public R<String> test(){
        return R.success("hello oauth");
    }
}
