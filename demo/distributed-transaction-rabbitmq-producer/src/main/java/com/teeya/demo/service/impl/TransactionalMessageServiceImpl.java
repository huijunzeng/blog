package com.teeya.demo.service.impl;

import com.teeya.demo.entity.pojo.TransactionalMessageEntity;
import com.teeya.demo.mapper.TransactionalMessageMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.demo.service.TransactionalMessageService;
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
public class TransactionalMessageServiceImpl extends ServiceImpl<TransactionalMessageMapper, TransactionalMessageEntity> implements TransactionalMessageService {

}
