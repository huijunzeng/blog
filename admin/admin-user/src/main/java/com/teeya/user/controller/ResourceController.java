package com.teeya.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.user.entity.form.ResourceSaveForm;
import com.teeya.user.entity.form.ResourceQueryForm;
import com.teeya.user.entity.form.UserUpdateForm;
import com.teeya.user.entity.pojo.ResourceEntity;
import com.teeya.user.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/resource")
@Api(value = "resource", tags = {"资源操作restful接口"})
@Slf4j
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "新增资源", notes = "新增资源")
    @ApiImplicitParam(paramType = "form", name = "resourceSaveForm", value = "资源新增表单", required = true, dataType = "ResourceSaveForm")
    @PostMapping
    public boolean save(@RequestBody ResourceSaveForm resourceSaveForm) {
        return resourceService.save(resourceSaveForm);
    }

    @ApiOperation(value = "修改资源", notes = "更新资源信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "资源id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "form", name = "resourceUpdateForm", value = "资源修改表单", required = true, dataType = "ResourceUpdateForm")})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable String id, @RequestBody UserUpdateForm resourceUpdateForm) {
        return resourceService.update(id, resourceUpdateForm);
    }

    @ApiOperation(value = "获取资源", notes = "根据资源id获取指定资源信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "资源id", required = true, dataType = "String")
    @GetMapping(value = "/{id}")
    public ResourceEntity get(@PathVariable String id) {
        log.info("resourceId: " + id);
        return resourceService.get(id);
    }

    @ApiOperation(value = "根据用户id获取相应的资源集合", notes = "根据用户id获取相应的资源集合")
    @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "String")
    @GetMapping
    public List<ResourceEntity> queryListByUserId(@RequestParam(value = "userId") String userId) {
        return resourceService.queryListByUserId(userId);
    }

    @ApiOperation(value = "根据用户名获取相应的资源集合", notes = "根据用户名获取相应的资源集合")
    @ApiImplicitParam(paramType = "path", name = "username", value = "用户名", required = true, dataType = "String")
    @GetMapping("/user/{username}")
    public List<ResourceEntity> queryListByUsername(@PathVariable(value = "username") String username) {
        return resourceService.queryListByUsername(username);
    }

    @ApiOperation(value = "根据角色code获取相应的资源集合", notes = "根据角色code获取相应的资源集合")
    @ApiImplicitParam(paramType = "path", name = "roleId", value = "角色code", required = true, dataType = "String")
    @GetMapping("/role/{roleId}")
    public List<ResourceEntity> queryListByRoleId(@PathVariable(value = "roleId") String roleId) {
        return resourceService.queryListByRoleId(roleId);
    }

    @ApiOperation(value = "搜索资源", notes = "根据条件获取资源信息列表")
    @ApiImplicitParam(paramType = "form", name = "resourceQueryForm", value = "资源查询参数", required = true, dataType = "ResourceQueryForm")
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
    @ApiImplicitParam(paramType = "path", name = "id", value = "资源id", required = true, dataType = "String")
    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable(value = "id") String id) {
        return resourceService.remove(id);
    }
}
