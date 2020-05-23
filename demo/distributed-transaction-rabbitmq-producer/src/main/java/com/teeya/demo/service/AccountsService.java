package com.teeya.demo.service;

import com.teeya.demo.entity.form.AccountSaveForm;
import com.teeya.demo.entity.pojo.AccountsEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
public interface AccountsService extends IService<AccountsEntity> {

    boolean save(AccountSaveForm accountSaveForm);
}
