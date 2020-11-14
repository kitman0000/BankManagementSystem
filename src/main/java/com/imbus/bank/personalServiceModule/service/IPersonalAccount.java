package com.imbus.bank.personalServiceModule.service;


import com.imbus.bank.componet.Entity.AccountEntity;
import com.imbus.bank.componet.Entity.CancelAccountEntity;
import com.imbus.bank.componet.type.CancelAccountResult;
import com.imbus.bank.componet.type.CreateAccountResult;
import com.imbus.bank.personalServiceModule.entity.PersonalAccountEntity;

public interface IPersonalAccount {
    CreateAccountResult createAccount(PersonalAccountEntity accountEntity);

    CancelAccountResult cancelAccount(CancelAccountEntity cancelAccountEntity);

    boolean isAllowCreateAccount();
}
