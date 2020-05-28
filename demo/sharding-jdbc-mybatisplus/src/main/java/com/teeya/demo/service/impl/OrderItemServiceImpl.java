package com.teeya.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.demo.entity.pojo.OrderItemEntity;
import com.teeya.demo.mapper.OrderItemMapper;
import com.teeya.demo.service.OrderItemService;
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
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItemEntity> implements OrderItemService {

}
