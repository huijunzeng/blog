package com.teeya.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.user.entity.form.UserSaveForm;
import com.teeya.user.entity.form.UserQueryForm;
import com.teeya.user.entity.form.UserUpdateForm;
import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Api(value = "user", tags = {"用户操作接口"})
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增用户", notes = "新增一个用户")
    @ApiImplicitParam(paramType = "form", name = "userSaveForm", value = "用户新增表单", required = true, dataType = "userSaveForm")
    @PostMapping
    public boolean save(@Validated @RequestBody UserSaveForm userSaveForm) {
        return userService.save(userSaveForm);
    }

    @ApiOperation(value = "修改用户", notes = "更新指定用户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "form", name = "userUpdateForm", value = "用户修改表单", required = true, dataType = "UserUpdateForm")})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable Long id, @Validated @RequestBody UserUpdateForm userUpdateForm) {
        return userService.update(id, userUpdateForm);
    }

    @ApiOperation(value = "获取用户", notes = "根据用户id获取指定用户信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataType = "Long")
    @GetMapping(value = "/{id}")
    public UserEntity get(@PathVariable Long id) {
        log.info("userId: " + id);
        return userService.get(id);
    }

    @ApiOperation(value = "获取用户", notes = "根据用户名/用户手机号码（唯一标识）获取指定用户信息")
    @ApiImplicitParam(paramType = "query", name = "uniqueId", value = "用户名/用户手机号码", required = true, dataType = "String")
    @GetMapping
    public UserEntity getByUniqueId(@RequestParam(value = "uniqueId") String uniqueId) {
        log.info("uniqueId: " + uniqueId);
        return userService.getByUniqueId(uniqueId);
    }

    @ApiOperation(value = "搜索用户", notes = "根据条件获取用户信息列表")
    @ApiImplicitParam(paramType = "form", name = "userQueryForm", value = "用户查询参数", required = true, dataType = "UserQueryForm")
    @PostMapping(value = "/list")
    public IPage queryList(@RequestBody UserQueryForm userQueryForm) {
        log.info("userQueryForm:{}", userQueryForm);
        return userService.queryList(userQueryForm);
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable Long id) {
        return userService.remove(id);
    }

    @GetMapping("/hello")
    public String test(){
        return "hello oauth";
    }
}
