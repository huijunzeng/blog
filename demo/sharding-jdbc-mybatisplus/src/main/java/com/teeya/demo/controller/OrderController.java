package com.teeya.demo.controller;


import com.teeya.demo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
@RestController
@RequestMapping("/order")
@Api(value = "order", tags = {"demo订单操作接口"})
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "创建order数据库", notes = "创建order数据库")
    @GetMapping(value = "/createTable")
    public boolean createTable() {
        return orderService.createTable();
    }

    @ApiOperation(value = "新增订单（测试分库分表新增）", notes = "新增一条订单记录")
    @PostMapping
    public boolean save() {
        return orderService.save();
    }

    @ApiOperation(value = "查询订单（测试广播表以及绑定表）", notes = "根据订单id查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "订单id", required = true, dataTypeClass = Long.class)
    @GetMapping(value = "/{id}")
    public Map get(@PathVariable Long id) {
        return orderService.get(id);
    }
}

