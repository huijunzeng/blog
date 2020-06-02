package com.teeya.demo.controller;


import com.teeya.demo.service.OrderItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemervice;

    @ApiOperation(value = "创建orderItem数据库", notes = "创建orderItem数据库")
    @GetMapping(value = "/createTable")
    public boolean createTable() {
        return orderItemervice.createTable();
    }
}

