package com.imbus.bank.componet.type;

/**
 * Created by zhong on 2020-8-19.
 */
public enum DemandWithdrawResult {
    DEMAND_WITHDRAW_SUCCESS,
    DEMAND_WITHDRAW_FAILED, // 账号密码错误
    NO_ENOUGH_BALANCE,
    NO_ENOUGH_CASH
}
