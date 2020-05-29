package com.teeya.demo.service.impl;

import com.teeya.demo.entity.pojo.TransactionalMessageEntity;
import com.teeya.demo.mapper.TransactionalMessageMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.demo.service.TransactionalMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class TransactionalMessageServiceImpl extends ServiceImpl<TransactionalMessageMapper, TransactionalMessageEntity> implements TransactionalMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(TransactionalMessageEntity transactionalMessageEntity, String content) {
        try {
            rabbitTemplate.convertAndSend(transactionalMessageEntity.getExchangeName(), transactionalMessageEntity.getRoutingKey(), content);
            if (log.isDebugEnabled()) {
                log.info("发送消息成功,目标队列:{},消息内容:{}", transactionalMessageEntity.getQueueName(), content);
            }
            // 发送成功后，更新消息记录的状态
            this.markSuccess(transactionalMessageEntity);
        } catch (Exception e) {
            // 发送失败，则做相对应的操作，比如重发
            log.error("发送消息失败,目标队列:{}", transactionalMessageEntity.getQueueName(), e);
            markFail(transactionalMessageEntity);
        }
    }

    private void markSuccess(TransactionalMessageEntity transactionalMessageEntity) {
        // 更新消息记录的状态
        transactionalMessageEntity.setStatus(1);
        super.updateById(transactionalMessageEntity);
    }

    private void markFail(TransactionalMessageEntity transactionalMessageEntity) {
        // 更新消息记录的状态
        transactionalMessageEntity.setStatus(1);
        super.updateById(transactionalMessageEntity);
    }
}
