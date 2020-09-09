package com.teeya.demo.controller;


import com.teeya.demo.entity.pojo.DictEntity;
import com.teeya.demo.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
@RestController
@RequestMapping("/dict")
@Api(value = "dict", tags = {"demo字典表操作接口"})
@Slf4j
public class DictController {

    @Autowired
    private DictService dictService;

    @ApiOperation(value = "创建数据库", notes = "创建数据库")
    @GetMapping(value = "/createTable")
    public boolean createTable() {
        return dictService.createTable();
    }

    @ApiOperation(value = "新增字典（测试广播表新增）", notes = "新增一条字典记录")
    @PostMapping
    public boolean save() {
        return dictService.save();
    }

    @ApiOperation(value = "查询字典单（测试广播表查询）", notes = "根据id查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "字典id", required = true, dataType = "Long")
    @GetMapping(value = "/{id}")
    public DictEntity get(@PathVariable Long id) {
        return dictService.get(id);
    }
}

