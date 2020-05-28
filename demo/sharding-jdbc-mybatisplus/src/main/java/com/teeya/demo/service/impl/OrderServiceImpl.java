package com.teeya.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.demo.entity.pojo.OrderEntity;
import com.teeya.demo.mapper.OrderMapper;
import com.teeya.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @Override
    public boolean save() {
        OrderEntity orderEntity = BeanUtils.instantiateClass(OrderEntity.class);
        log.info("insert_accountsEntity=======: " + orderEntity.toString());
        return super.save(orderEntity);
    }
}
