package com.teeya.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.user.entity.form.UserForm;
import com.teeya.user.entity.form.UserQueryForm;
import com.teeya.user.entity.form.UserUpdateForm;
import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
@Api(value = "user", tags = {"用户操作接口"})
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增用户", notes = "新增一个用户")
    @ApiImplicitParam(name = "userForm", value = "用户新增表单", required = true, dataType = "UserForm")
    @PostMapping
    public boolean insert(@Valid @RequestBody UserForm userForm) {
        return userService.insert(userForm);
    }

    @ApiOperation(value = "修改用户", notes = "更新指定用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "userUpdateForm", value = "用户修改表单", required = true, dataType = "UserUpdateForm")})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable String id, @RequestBody UserUpdateForm userUpdateForm) {
        return userService.update(id, userUpdateForm);
    }

    @ApiOperation(value = "获取用户", notes = "根据用户id获取指定用户信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public UserEntity get(@PathVariable String id) {
        log.info("userId: " + id);
        return userService.get(id);
    }

    @ApiOperation(value = "获取用户", notes = "根据用户名/用户手机号码获取指定用户信息")
    @ApiImplicitParam(paramType = "query", name = "uniqueId", value = "用户名/用户手机号码", required = true, dataType = "string")
    @GetMapping
    public UserEntity query(@RequestParam(value = "uniqueId") String uniqueId) {
        log.info("uniqueId: " + uniqueId);
        return userService.getByUniqueId(uniqueId);
    }

    @ApiOperation(value = "搜索用户", notes = "根据条件获取用户信息列表")
    @ApiImplicitParam(name = "userQueryForm", value = "用户查询参数", required = true, dataType = "UserQueryForm")
    @PostMapping(value = "/list")
    public IPage query(@RequestBody UserQueryForm userQueryForm) {
        log.info("userQueryForm:{}", userQueryForm);
        return userService.queryList(userQueryForm);
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataType = "string")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable(value = "id") String id) {
        return userService.delete(id);
    }

    @GetMapping("/hello")
    public String test(){
        return "hello oauth";
    }
}
