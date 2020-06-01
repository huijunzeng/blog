package com.teeya.demo.controller;


import com.teeya.demo.entity.pojo.OrderEntity;
import com.teeya.demo.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "新增订单（测试分库分表新增）", notes = "新增一条订单记录")
    @PostMapping
    public boolean save() {
        return orderService.save();
    }

    @ApiOperation(value = "查询订单（测试广播表以及绑定表）", notes = "根据订单id查询")
    @GetMapping(value = "/{id}")
    public OrderEntity get(@PathVariable Long id) {
        return orderService.get(id);
    }
}

