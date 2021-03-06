package com.teeya.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.user.entity.form.ResourceSaveForm;
import com.teeya.user.entity.form.ResourceQueryForm;
import com.teeya.user.entity.form.ResourceUpdateForm;
import com.teeya.user.entity.pojo.ResourceEntity;
import com.teeya.user.service.ResourceService;
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
@RequestMapping("/resource")
@Api(value = "resource", tags = {"资源操作restful接口"})
@Slf4j
@Validated
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "新增资源", notes = "新增资源")
    @ApiImplicitParam(paramType = "form", name = "resourceSaveForm", value = "资源新增表单", required = true, dataTypeClass = ResourceSaveForm.class)
    @PostMapping
    public boolean save(@Validated @RequestBody ResourceSaveForm resourceSaveForm) {
        return resourceService.save(resourceSaveForm);
    }

    @ApiOperation(value = "修改资源", notes = "更新资源信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "资源id", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(paramType = "form", name = "resourceUpdateForm", value = "资源修改表单", required = true, dataTypeClass = ResourceUpdateForm.class)})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable Long id, @Validated @RequestBody ResourceUpdateForm resourceUpdateForm) {
        return resourceService.update(id, resourceUpdateForm);
    }

    @ApiOperation(value = "获取资源", notes = "根据资源id获取指定资源信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "资源id", required = true, dataTypeClass = Long.class)
    @GetMapping(value = "/{id}")
    public ResourceEntity get(@PathVariable Long id) {
        log.info("resourceId: " + id);
        return resourceService.get(id);
    }

    @ApiOperation(value = "根据用户id获取相应的资源集合", notes = "根据用户id获取相应的资源集合")
    @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataTypeClass = Long.class)
    @GetMapping
    public List<ResourceEntity> queryListByUserId(@NotNull(message = "用户id不能为空") @RequestParam(value = "userId") Long userId) {
        return resourceService.queryListByUserId(userId);
    }

    @ApiOperation(value = "根据用户名获取相应的资源集合", notes = "根据用户名获取相应的资源集合")
    @ApiImplicitParam(paramType = "path", name = "username", value = "用户名", required = true, dataTypeClass = String.class)
    @GetMapping("/user/{username}")
    public List<ResourceEntity> queryListByUsername(@PathVariable String username) {
        return resourceService.queryListByUsername(username);
    }

    @ApiOperation(value = "根据角色code获取相应的资源集合", notes = "根据角色code获取相应的资源集合")
    @ApiImplicitParam(paramType = "path", name = "roleId", value = "角色code", required = true, dataTypeClass = Long.class)
    @GetMapping("/role/{roleId}")
    public List<ResourceEntity> queryListByRoleId(@PathVariable Long roleId) {
        return resourceService.queryListByRoleId(roleId);
    }

    @ApiOperation(value = "搜索资源", notes = "根据条件获取资源信息列表")
    @ApiImplicitParam(paramType = "form", name = "resourceQueryForm", value = "资源查询参数", required = true, dataTypeClass = ResourceQueryForm.class)
    @PostMapping(value = "/list")
    public IPage queryList(@RequestBody ResourceQueryForm resourceQueryForm) {
        log.info("resourceQueryForm:{}", resourceQueryForm);
        return resourceService.queryList(resourceQueryForm);
    }

    @ApiOperation(value = "获取所有资源集合", notes = "获取所有资源集合")
    @GetMapping("/all")
    public List<ResourceEntity> getAll() {
        return resourceService.getAll();
    }

    @ApiOperation(value = "删除资源", notes = "根据id删除资源")
    @ApiImplicitParam(paramType = "path", name = "id", value = "资源id", required = true, dataTypeClass = Long.class)
    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable Long id) {
        return resourceService.remove(id);
    }
}
