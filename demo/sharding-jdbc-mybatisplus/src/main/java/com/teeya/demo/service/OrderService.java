package com.teeya.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.demo.entity.pojo.OrderEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
public interface OrderService extends IService<OrderEntity> {

    boolean save();
}
