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

import javax.validation.constraints.NotBlank;

/**
 * 直接在方法内校验非实体类的单个参数时，需要加上@Validated注解（加@Valid无效）
 */
@RestController
@RequestMapping("/user")
@Api(value = "user", tags = {"用户操作接口"})
@Slf4j
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增用户", notes = "新增一个用户")
    @ApiImplicitParam(paramType = "form", name = "userSaveForm", value = "用户新增表单", required = true, dataTypeClass = UserSaveForm.class)
    @PostMapping
    public boolean save(@Validated @RequestBody UserSaveForm userSaveForm) {
        return userService.save(userSaveForm);
    }

    @ApiOperation(value = "修改用户", notes = "更新指定用户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(paramType = "form", name = "userUpdateForm", value = "用户修改表单", required = true, dataTypeClass = UserUpdateForm.class)})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable Long id, @Validated @RequestBody UserUpdateForm userUpdateForm) {
        return userService.update(id, userUpdateForm);
    }

    @ApiOperation(value = "获取用户", notes = "根据用户id获取指定用户信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataTypeClass = Long.class)
    @GetMapping(value = "/{id}")
    public UserEntity get(@PathVariable Long id) {
        log.info("userId: " + id);
        return userService.get(id);
    }

    @ApiOperation(value = "获取用户", notes = "根据用户名/用户手机号码（唯一标识）获取指定用户信息")
    @ApiImplicitParam(paramType = "query", name = "uniqueId", value = "用户名/用户手机号码", required = true, dataTypeClass = String.class)
    @GetMapping
    public UserEntity getByUniqueId(@NotBlank(message = "唯一标识不能为空") String uniqueId) {
        log.info("uniqueId: " + uniqueId);
        return userService.getByUniqueId(uniqueId);
    }

    @ApiOperation(value = "搜索用户", notes = "根据条件获取用户信息列表")
    @ApiImplicitParam(paramType = "form", name = "userQueryForm", value = "用户查询参数", required = true, dataTypeClass = UserQueryForm.class)
    @PostMapping(value = "/list")
    public IPage queryList(@RequestBody UserQueryForm userQueryForm) {
        log.info("userQueryForm:{}", userQueryForm);
        return userService.queryList(userQueryForm);
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataTypeClass = Long.class)
    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable Long id) {
        return userService.remove(id);
    }

    @GetMapping("/hello")
    public String test(){
        return "hello oauth";
    }
}
