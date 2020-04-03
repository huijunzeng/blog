package com.teeya.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.user.entity.form.RoleForm;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Api(value = "role", tags = {"角色操作接口"})
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @ApiImplicitParam(paramType = "form", name = "roleForm", value = "角色新增表单", required = true, dataType = "roleForm")
    @PostMapping
    public boolean insert(@RequestBody RoleForm roleForm) {
        return roleService.insert(roleForm);
    }


    @ApiOperation(value = "修改角色", notes = "更新指定角色信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "roleUpdateForm", value = "角色修改表单", required = true, dataType = "RoleUpdateForm")})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable String id, @RequestBody RoleUpdateForm roleUpdateForm) {
        return roleService.update(id, roleUpdateForm);
    }

    @ApiOperation(value = "获取角色", notes = "根据角色id获取指定角色信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "角色id", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public RoleEntity get(@PathVariable String id) {
        log.info("roleId: " + id);
        return roleService.getById(id);
    }

    @ApiOperation(value = "根据用户id获取相应的角色集合", notes = "根据用户id获取相应的角色集合")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true, dataType = "string")
    @GetMapping("/user/{userId}")
    public List<RoleEntity> query(@PathVariable(value = "userId") String userId) {
        return roleService.queryListByUserId(userId);
    }

    @ApiOperation(value = "搜索角色", notes = "根据条件获取角色信息列表")
    @ApiImplicitParam(name = "roleQueryForm", value = "角色查询参数", required = true, dataType = "RoleQueryForm")
    @PostMapping(value = "/list")
    public IPage query(@RequestBody RoleQueryForm roleQueryForm) {
        log.info("roleQueryForm:{}", roleQueryForm);
        return roleService.queryList(roleQueryForm);
    }

    @ApiOperation(value = "搜索角色", notes = "获取所有角色信息列表")
    @GetMapping(value = "/all")
    public List<RoleEntity> get() {
        return roleService.getAll();
    }
}
