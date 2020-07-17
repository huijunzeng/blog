package com.teeya.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.user.entity.form.RoleSaveForm;
import com.teeya.user.entity.form.RoleQueryForm;
import com.teeya.user.entity.form.RoleUpdateForm;
import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 直接在方法内校验非实体类的单个参数时，需要加上@Validated注解（加@Valid无效）
 */
@RestController
@RequestMapping("/role")
@Api(value = "role", tags = {"角色操作接口"})
@Slf4j
@Validated
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @ApiImplicitParam(paramType = "form", name = "roleSaveForm", value = "角色新增表单", required = true, dataType = "RoleSaveForm")
    @PostMapping
    public boolean save(@Validated @RequestBody RoleSaveForm roleSaveForm) {
        return roleService.save(roleSaveForm);
    }


    @ApiOperation(value = "修改角色", notes = "更新指定角色信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "form", name = "roleUpdateForm", value = "角色修改表单", required = true, dataType = "RoleUpdateForm")})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable Long id, @Validated @RequestBody RoleUpdateForm roleUpdateForm) {
        return roleService.update(id, roleUpdateForm);
    }

    @ApiOperation(value = "获取角色", notes = "根据角色id获取指定角色信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "角色id", required = true, dataType = "Long")
    @GetMapping(value = "/{id}")
    public RoleEntity get(@PathVariable Long id) {
        log.info("roleId: " + id);
        return roleService.get(id);
    }

    @ApiOperation(value = "根据用户id获取相应的角色集合", notes = "根据用户id获取相应的角色集合")
    @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "Long")
    @GetMapping
    @SentinelResource(value = "/role", blockHandler = "block", fallback = "fallback") //使用sentinel处理熔断降级demo
    public List<RoleEntity> queryListByUserId(@NotNull(message = "用户id不能为空") @RequestParam(value = "userId") Long userId) {
        return roleService.queryListByUserId(userId);
    }

    public String block(){ return "blocked"; }
    public String fallback(){ return "fallbacked"; }

    @ApiOperation(value = "根据用户名获取相应的角色集合", notes = "根据用户名获取相应的角色集合")
    @ApiImplicitParam(paramType = "path", name = "username", value = "用户名", required = true, dataType = "String")
    @GetMapping("/user/{username}")
    public List<RoleEntity> queryListByUsername(@PathVariable String username) {
        return roleService.queryListByUsername(username);
    }

    @ApiOperation(value = "搜索角色", notes = "根据条件获取角色信息列表")
    @ApiImplicitParam(paramType = "form", name = "roleQueryForm", value = "角色查询参数", required = true, dataType = "RoleQueryForm")
    @PostMapping(value = "/list")
    public IPage queryList(@RequestBody RoleQueryForm roleQueryForm) {
        log.info("roleQueryForm:{}", roleQueryForm);
        return roleService.queryList(roleQueryForm);
    }

    @ApiOperation(value = "搜索角色", notes = "获取所有角色信息列表")
    @GetMapping(value = "/all")
    public List<RoleEntity> getAll() {
        return roleService.getAll();
    }

    @ApiOperation(value = "删除角色", notes = "根据id删除角色")
    @ApiImplicitParam(paramType = "path", name = "id", value = "角色id", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable Long id) {
        return roleService.remove(id);
    }

}
