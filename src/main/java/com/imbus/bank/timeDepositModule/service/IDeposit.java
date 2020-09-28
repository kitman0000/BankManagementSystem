package com.imbus.bank.timeDepositModule.service;

import com.imbus.bank.timeDepositModule.bo.DepositBo;

import java.util.List;

/**
 * Created by zhong on 2020-9-7.
 */
public interface IDeposit {
    List<DepositBo> getAccountDeposit(String accountID);
}
