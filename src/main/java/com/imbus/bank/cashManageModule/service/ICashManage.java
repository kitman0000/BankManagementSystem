package com.imbus.bank.cashManageModule.service;

import com.imbus.bank.cashManageModule.type.CashToFundResult;
import com.imbus.bank.cashManageModule.type.FundToCashResult;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-9-14.
 */
public interface ICashManage {
    CashToFundResult cashToFund(int agencyID, BigDecimal amount);

    FundToCashResult fundToCash(int agencyID,BigDecimal amount);

    BigDecimal getAgencyCash(int agencyID);
}
