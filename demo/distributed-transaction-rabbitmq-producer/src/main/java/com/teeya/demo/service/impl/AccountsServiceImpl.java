package com.teeya.demo.service.impl;

import com.teeya.demo.entity.form.AccountSaveForm;
import com.teeya.demo.entity.pojo.AccountsEntity;
import com.teeya.demo.entity.pojo.TransactionalMessageEntity;
import com.teeya.demo.mapper.AccountsMapper;
import com.teeya.demo.service.AccountsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teeya.demo.service.TransactionalMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    private TransactionalMessageService transactionalMessageService;

    @Override
    public boolean save(AccountSaveForm accountSaveForm) {
        AccountsEntity accountsEntity = BeanUtils.instantiateClass(AccountsEntity.class);
        BeanUtils.copyProperties(accountSaveForm, accountsEntity);
        log.info("insert_accountsEntity=======: " + accountsEntity.toString());
        boolean save1 = super.save(accountsEntity);
        TransactionalMessageEntity transactionalMessageEntity = new TransactionalMessageEntity();
        transactionalMessageEntity.setQueueName("1");
        transactionalMessageEntity.setExchangeName("1");
        transactionalMessageEntity.setExchangeType("topic");
        transactionalMessageEntity.setRoutingKey("test");
        String content = accountSaveForm.toString();
        transactionalMessageEntity.setMessage(content);
        // 保存事务消息记录
        boolean save = transactionalMessageService.save(transactionalMessageEntity);

        //--------------------------以上执行成功后，才会执行以下操作-----------------------------
        // 注册事务同步器
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                // 给mq服务器发送消息
                transactionalMessageService.sendMessage(transactionalMessageEntity, content);
            }
        });
        return true;
    }
}
