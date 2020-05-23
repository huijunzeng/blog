package com.teeya.demo.service.impl;

import com.teeya.demo.entity.form.AccountSaveForm;
import com.teeya.demo.entity.pojo.AccountsEntity;
import com.teeya.demo.mapper.AccountsMapper;
import com.teeya.demo.service.AccountsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AccountsServiceImpl extends ServiceImpl<AccountsMapper, AccountsEntity> implements AccountsService {

    @Override
    public boolean save(AccountSaveForm accountSaveForm) {
        AccountsEntity accountsEntity = BeanUtils.instantiateClass(AccountsEntity.class);
        BeanUtils.copyProperties(accountSaveForm, accountsEntity);
        log.info("insert_accountsEntity=======: " + accountsEntity.toString());
        return super.save(accountsEntity);
    }
}
