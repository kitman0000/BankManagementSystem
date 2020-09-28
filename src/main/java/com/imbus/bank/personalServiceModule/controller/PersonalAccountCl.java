package com.imbus.bank.personalServiceModule.controller;

import com.imbus.bank.componet.Entity.AccountEntity;
import com.imbus.bank.componet.Entity.CancelAccountEntity;
import com.imbus.bank.componet.IAccountService;
import com.imbus.bank.componet.type.CancelAccountResult;
import com.imbus.bank.componet.type.CreateAccountResult;
import com.imbus.bank.personalServiceModule.entity.PersonalAccountEntity;
import com.imbus.bank.personalServiceModule.service.IPersonalAccount;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "新增个人账户接口")
@RestController
public class PersonalAccountCl{
    @Autowired
    IPersonalAccount personalAccount;

    @RequestMapping(value = "/api/personalService/account",method = RequestMethod.POST)
    public CreateAccountResult createAccount(PersonalAccountEntity accountEntity) {
        return personalAccount.createAccount(accountEntity);
    }

    @RequestMapping(value = "/api/personalService/account",method = RequestMethod.DELETE)
    public CancelAccountResult cancelAccount(CancelAccountEntity cancelAccountEntity) {

        return personalAccount.cancelAccount(cancelAccountEntity);
    }
}
