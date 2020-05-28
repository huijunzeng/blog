package com.teeya.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.demo.entity.pojo.OrderEntity;
import com.teeya.demo.entity.pojo.OrderItemEntity;
import com.teeya.demo.mapper.OrderMapper;
import com.teeya.demo.service.OrderItemService;
import com.teeya.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public boolean save() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId("8");
        orderEntity.setUserId("12");
        log.info("insert_orderEntity=======: " + orderEntity.toString());
        super.save(orderEntity);
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setUserId("12");
        orderItemEntity.setOrderId(orderEntity.getId());
        orderItemEntity.setItemName("三只松鼠芒果干");
        log.info("insert_orderItemEntity=======: " + orderItemEntity.toString());
        return orderItemService.save(orderItemEntity);
    }
}
