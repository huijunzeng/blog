package com.teeya.demo.controller;


import com.teeya.demo.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "新增订单（测试）", notes = "新增一条订单记录")
    @PostMapping
    public boolean save(@RequestParam String userId) {
        return orderService.save();
    }
}

