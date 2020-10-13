package com.teeya.demo.controller;


import com.teeya.demo.entity.form.AccountSaveForm;
import com.teeya.demo.service.AccountsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
@RestController
@RequestMapping("/accounts")
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @ApiOperation(value = "新增用户", notes = "新增一个账号")
    @ApiImplicitParam(paramType = "body", name = "userSaveForm", value = "账号新增表单", required = true, dataTypeClass = AccountSaveForm.class)
    @PostMapping
    public boolean save(@RequestBody AccountSaveForm accountSaveForm) {
        return accountsService.save(accountSaveForm);
    }
}

