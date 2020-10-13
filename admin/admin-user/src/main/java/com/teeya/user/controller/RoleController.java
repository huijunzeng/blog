package com.teeya.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.common.core.entity.vo.R;
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
    @ApiImplicitParam(paramType = "body", name = "roleSaveForm", value = "角色新增表单", required = true, dataTypeClass = RoleSaveForm.class)
    @PostMapping
    public R<Boolean> save(@Validated @RequestBody RoleSaveForm roleSaveForm) {
        return R.success(roleService.save(roleSaveForm));
    }


    @ApiOperation(value = "修改角色", notes = "更新指定角色信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "角色id", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(paramType = "body", name = "roleUpdateForm", value = "角色修改表单", required = true, dataTypeClass = RoleUpdateForm.class)})
    @PutMapping(value = "/{id}")
    public R<Boolean> update(@PathVariable Long id, @Validated @RequestBody RoleUpdateForm roleUpdateForm) {
        return R.success(roleService.update(id, roleUpdateForm));
    }

    @ApiOperation(value = "获取角色", notes = "根据角色id获取指定角色信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "角色id", required = true, dataTypeClass = Long.class)
    @GetMapping(value = "/{id}")
    public R<RoleEntity> get(@PathVariable Long id) {
        log.info("roleId: " + id);
        return R.success(roleService.get(id));
    }

    @ApiOperation(value = "根据用户id获取相应的角色集合", notes = "根据用户id获取相应的角色集合")
    @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataTypeClass = Long.class)
    @GetMapping
    @SentinelResource(value = "/role", blockHandler = "block", fallback = "fallback") //使用sentinel处理熔断降级demo
    public R<List<RoleEntity>> queryListByUserId(@NotNull(message = "用户id不能为空") @RequestParam(value = "userId") Long userId) {
        return R.success(roleService.queryListByUserId(userId));
    }

    @ApiOperation(value = "根据用户名获取相应的角色集合", notes = "根据用户名获取相应的角色集合")
    @ApiImplicitParam(paramType = "path", name = "username", value = "用户名", required = true, dataTypeClass = String.class)
    @GetMapping("/user/{username}")
    public R<List<RoleEntity>> queryListByUsername(@PathVariable String username) {
        return R.success(roleService.queryListByUsername(username));
    }

    @ApiOperation(value = "搜索角色", notes = "根据条件获取角色信息列表")
    @ApiImplicitParam(paramType = "body", name = "roleQueryForm", value = "角色查询参数", required = true, dataTypeClass = RoleQueryForm.class)
    @PostMapping(value = "/list")
    public R<IPage> queryList(@RequestBody RoleQueryForm roleQueryForm) {
        log.info("roleQueryForm:{}", roleQueryForm);
        return R.success(roleService.queryList(roleQueryForm));
    }

    @ApiOperation(value = "搜索角色", notes = "获取所有角色信息列表")
    @GetMapping(value = "/all")
    public R<List<RoleEntity>> getAll() {
        return R.success(roleService.getAll());
    }

    @ApiOperation(value = "删除角色", notes = "根据id删除角色")
    @ApiImplicitParam(paramType = "path", name = "id", value = "角色id", required = true, dataTypeClass = Long.class)
    @DeleteMapping("/{id}")
    public R<Boolean> remove(@PathVariable Long id) {
        return R.success(roleService.remove(id));
    }

}
