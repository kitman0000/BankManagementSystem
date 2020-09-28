package com.imbus.bank.trunkModule.service;

import com.imbus.bank.trunkModule.bo.TrunkCardBo;
import com.imbus.bank.trunkModule.type.UpdateTrunkCashResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-9-11.
 */
public interface ITrunkCash {
    UpdateTrunkCashResult addTrunkCash(int id, BigDecimal amount);

    UpdateTrunkCashResult removeTrunkCash(int id,BigDecimal amount);
}
