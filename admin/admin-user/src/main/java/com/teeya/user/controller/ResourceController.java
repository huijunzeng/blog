package com.teeya.user.controller;

import com.teeya.common.entity.vo.Result;
import com.teeya.user.entity.form.ResourceForm;
import com.teeya.user.entity.pojo.ResourceEntity;
import com.teeya.user.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
    @ApiImplicitParam(paramType = "form", name = "resourceForm", value = "资源表单", required = true, dataType = "resourceForm")
    @PostMapping
    public Result insert(@RequestBody ResourceForm resourceForm) throws Exception {
        return Result.success(resourceService.insert(resourceForm));
    }

    @ApiOperation(value = "根据用户id获取相应的资源集合", notes = "根据用户id获取相应的资源集合")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户ID", required = true, dataType = "string")
    @GetMapping("/user/{userId}")
    public Result queryListByUserId(@PathVariable(value = "userId") String userId) {
        return Result.success(resourceService.queryListByUserId(userId));
    }

    @ApiOperation(value = "获取所有资源集合", notes = "获取所有资源集合")
    @GetMapping("/all")
    public Result queryAll() {
        return Result.success(resourceService.queryAll());
    }

}
