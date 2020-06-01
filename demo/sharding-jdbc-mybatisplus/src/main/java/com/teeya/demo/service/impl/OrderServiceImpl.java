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
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public boolean save() {
        /*for (int i = 0; i < 20; i++) {
            long userId = i;
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setUserId(userId);
            log.info("insert_orderEntity=======: " + orderEntity.toString());
            super.save(orderEntity);
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setUserId(userId);
            orderItemEntity.setOrderId(orderEntity.getId());
            log.info("insert_orderEntity_id=======: " + orderEntity.getId());
            orderItemEntity.setItemName("三只松鼠芒果干");
            log.info("insert_orderItemEntity=======: " + orderItemEntity.toString());
            orderItemService.save(orderItemEntity);
        }*/
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(80L);
        orderEntity.setId(1266338382508789762L);
        log.info("insert_orderEntity=======: " + orderEntity.toString());
        super.save(orderEntity);
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setUserId(80L);
        orderItemEntity.setOrderId(orderEntity.getId());
        log.info("insert_orderEntity_id=======: " + orderEntity.getId());
        orderItemEntity.setItemName("三只松鼠芒果干");
        log.info("insert_orderItemEntity=======: " + orderItemEntity.toString());
        orderItemService.save(orderItemEntity);
        return true;
    }

    @Override
    public OrderEntity get(Long id) {
        return orderMapper.getDetailById(id);
    }
}
