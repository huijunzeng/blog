package com.teeya.user.controller;

import com.teeya.user.entity.form.UserForm;
import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/user")
@Api(value = "user", tags = {"用户操作接口"})
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增用户", notes = "新增一个用户")
    @ApiImplicitParam(name = "userForm", value = "新增用户form表单", required = true, dataType = "UserForm")
    @PostMapping
    public void insert(@Valid @RequestBody UserForm userForm) {
        userService.insert(userForm);
    }


    @ApiOperation(value = "获取用户", notes = "根据用户名获取指定用户信息")
    @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "string")
    @GetMapping
    public UserEntity queryByUsername(HttpServletRequest request,  @RequestParam(value = "username") String username) {
        log.info("username: " + username);
        return userService.queryByUsername(username);
    }

    @ApiOperation(value = "获取用户", notes = "根据用户手机号码获取指定用户信息")
    @ApiImplicitParam(paramType = "query", name = "phone", value = "用户手机号码", required = true, dataType = "string")
    @GetMapping("/selectByPhone")
    public UserEntity selectByPhone(@RequestParam(value = "phone") String phone) {
        return userService.selectByPhone(phone);
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataType = "string")
    @DeleteMapping("/{id}")
    public int delete(@PathVariable(value = "id") String id) {
        return userService.delete(id);
    }

    @GetMapping("/hello")
    public String test(){
        return "hello oauth";
    }
}
