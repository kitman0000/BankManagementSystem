package com.imbus.bank.componet;

import com.imbus.bank.componet.Entity.AccountEntity;
import com.imbus.bank.componet.Entity.CancelAccountEntity;
import com.imbus.bank.componet.type.CancelAccountResult;
import com.imbus.bank.componet.type.CreateAccountResult;

/**
 * Created by zhong on 2020-8-11.
 *
 * 账户业务接口
 * 所有账户业务Controller层，都必须实现此接口
 */
public interface IAccountService {
    CreateAccountResult createAccount(AccountEntity accountEntity);

    CancelAccountResult cancelAccount(CancelAccountEntity cancelAccountEntity);
}
