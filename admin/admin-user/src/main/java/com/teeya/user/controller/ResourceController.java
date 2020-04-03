package com.teeya.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.user.entity.form.ResourceForm;
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
    @ApiImplicitParam(paramType = "form", name = "resourceForm", value = "资源新增表单", required = true, dataType = "resourceForm")
    @PostMapping
    public boolean insert(@RequestBody ResourceForm resourceForm) {
        return resourceService.insert(resourceForm);
    }

    @ApiOperation(value = "修改资源", notes = "更新资源信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "资源id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "resourceUpdateForm", value = "资源修改表单", required = true, dataType = "ResourceUpdateForm")})
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable String id, @RequestBody UserUpdateForm resourceUpdateForm) {
        return resourceService.update(id, resourceUpdateForm);
    }

    @ApiOperation(value = "获取资源", notes = "根据资源id获取指定资源信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "资源id", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public ResourceEntity get(@PathVariable String id) {
        log.info("resourceId: " + id);
        return resourceService.get(id);
    }

   /* @ApiOperation(value = "根据用户id获取相应的资源集合", notes = "根据用户id获取相应的资源集合")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户ID", required = true, dataType = "string")
    @GetMapping("/user/{userId}")
    public List<ResourceEntity> queryListByUserId(@PathVariable(value = "userId") String userId) {
        return resourceService.queryListByUserId(userId);
    }*/

    @ApiOperation(value = "根据用户名获取相应的资源集合", notes = "根据用户名获取相应的资源集合")
    @ApiImplicitParam(paramType = "path", name = "username", value = "用户名", required = true, dataType = "string")
    @GetMapping("/user/{username}")
    public List<ResourceEntity> queryListByUserId(@PathVariable(value = "username") String username) {
        return resourceService.queryListByUsername(username);
    }

    @ApiOperation(value = "搜索资源", notes = "根据条件获取资源信息列表")
    @ApiImplicitParam(name = "resourceQueryForm", value = "资源查询参数", required = true, dataType = "ResourceQueryForm")
    @PostMapping(value = "/list")
    public IPage query(@RequestBody ResourceQueryForm resourceQueryForm) {
        log.info("resourceQueryForm:{}", resourceQueryForm);
        return resourceService.queryList(resourceQueryForm);
    }

    @ApiOperation(value = "获取所有资源集合", notes = "获取所有资源集合")
    @GetMapping("/all")
    public List<ResourceEntity> get() {
        return resourceService.getAll();
    }

}
