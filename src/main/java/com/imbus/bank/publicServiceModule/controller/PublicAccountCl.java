package com.imbus.bank.publicServiceModule.controller;

import com.imbus.bank.componet.Entity.AccountEntity;
import com.imbus.bank.componet.Entity.CancelAccountEntity;
import com.imbus.bank.componet.IAccountService;
import com.imbus.bank.componet.type.CancelAccountResult;
import com.imbus.bank.componet.type.CreateAccountResult;
import com.imbus.bank.publicServiceModule.entity.PublicAccountEntity;
import com.imbus.bank.publicServiceModule.service.IPublicAccount;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2020-8-11.
 */
@Api(description = "新增账户接口")
@RestController
public class PublicAccountCl {

    @Autowired
    IPublicAccount publicAccount;

    @RequestMapping(value = "/api/publicAccount/account",method = RequestMethod.POST)
    public CreateAccountResult createAccount(PublicAccountEntity publicAccountEntity) {
        return publicAccount.createAccount(publicAccountEntity);
    }

    @RequestMapping(value = "/api/publicAccount/account",method = RequestMethod.DELETE)
    public CancelAccountResult cancelAccount(CancelAccountEntity cancelAccountEntity) {
        return publicAccount.cancelAccount(cancelAccountEntity);
    }
}
