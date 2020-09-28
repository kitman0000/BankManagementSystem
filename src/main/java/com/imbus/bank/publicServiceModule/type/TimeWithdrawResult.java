package com.imbus.bank.publicServiceModule.type;

/**
 * Created by zhong on 2020-8-23.
 */
public enum TimeWithdrawResult {
    WITHDRAW_SUCCESS,
    ACCOUNT_ERROR, // 账号不匹配
    WITHDRAW_FAILED, // 密码错误
    NO_ENOUGH_CASH // 现金不足
}
