package com.teeya.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.teeya.demo.entity.form.AccountSaveForm;
import com.teeya.demo.entity.pojo.AccountsEntity;
import com.teeya.demo.entity.pojo.TransactionalMessageEntity;
import com.teeya.demo.mapper.AccountsMapper;
import com.teeya.demo.service.AccountsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.demo.service.TransactionalMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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
public class AccountsServiceImpl extends ServiceImpl<AccountsMapper, AccountsEntity> implements AccountsService {

    /**
     * 积分处理队列
     */
    @Value("${base.config.rabbitmq.points-queue}")
    public String POINTS_QUEUE_NAME = "points.queue";
    /**
     * 积分重试队列
     */
    @Value("${base.config.rabbitmq.points-retry-queue}")
    public String POINTS_RETRY_QUEUE_NAME = "points.retry.queue";
    /**
     * 积分处理路由KEY
     */
    @Value("${base.config.rabbitmq.points-route-key}")
    public String POINTS_ROUTE_NAME = "points.route.key";
    /**
     * 积分处理交换机
     */
    @Value("${base.config.rabbitmq.points-exchange}")
    public String POINTS_EXCHANGE_NAME = "points.exchange";

    @Autowired
    private TransactionalMessageService transactionalMessageService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(AccountSaveForm accountSaveForm) {
        AccountsEntity accountsEntity = BeanUtils.instantiateClass(AccountsEntity.class);
        BeanUtils.copyProperties(accountSaveForm, accountsEntity);
        log.info("insert_accountsEntity=======: " + accountsEntity.toString());
        boolean save1 = super.save(accountsEntity);
        TransactionalMessageEntity transactionalMessageEntity = new TransactionalMessageEntity();
        transactionalMessageEntity.setQueueName(POINTS_QUEUE_NAME);
        transactionalMessageEntity.setExchangeName(POINTS_EXCHANGE_NAME);
        transactionalMessageEntity.setExchangeType(ExchangeTypes.DIRECT);
        transactionalMessageEntity.setRoutingKey(POINTS_ROUTE_NAME);
        transactionalMessageEntity.setStatus(0);
        String content = JSONObject.toJSONString(accountsEntity);
        transactionalMessageEntity.setMessage(content);
        // 保存事务消息记录
        boolean save = transactionalMessageService.save(transactionalMessageEntity);

        //--------------------------以上执行成功后，才会执行以下操作-----------------------------
        // 注册事务同步器
        /*TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                // 给mq服务器发送消息
                transactionalMessageService.sendMessage(transactionalMessageEntity, content);
            }
        });*/
        transactionalMessageService.sendMessage(transactionalMessageEntity, content);
        return true;
    }
}
