package com.teeya.user.controller;

import com.teeya.common.entity.vo.Result;
import com.teeya.user.entity.form.RoleForm;
import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/role")
@Api(value = "role", tags = {"角色操作接口"})
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @ApiImplicitParam(paramType = "form", name = "roleForm", value = "用户角色表单", required = true, dataType = "roleForm")
    @PostMapping
    public Result insert(@RequestBody RoleForm roleForm) throws Exception {
        return Result.success(roleService.insert(roleForm));
    }

    @ApiOperation(value = "根据用户id获取相应的角色集合", notes = "根据用户id获取相应的角色集合")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户ID", required = true, dataType = "string")
    @GetMapping("/user/{userId}")
    public Result queryListByUserId(@PathVariable(value = "userId") String userId) {
        return Result.success(roleService.queryListByUserId(userId));
    }

}
