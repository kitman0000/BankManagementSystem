package com.imbus.bank.publicServiceModule.service;

import com.imbus.bank.componet.Entity.CancelAccountEntity;
import com.imbus.bank.componet.type.CancelAccountResult;
import com.imbus.bank.componet.type.CreateAccountResult;
import com.imbus.bank.publicServiceModule.entity.PublicAccountEntity;

/**
 * Created by zhong on 2020-8-11.
 */
public interface IPublicAccount {
    CreateAccountResult createAccount(PublicAccountEntity accountEntity);

    CancelAccountResult cancelAccount(CancelAccountEntity cancelAccountEntity);

    boolean isAllowCreateAccount();
}
