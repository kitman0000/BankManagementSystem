package com.imbus.bank.componet.Entity;

import lombok.Data;

import java.math.BigDecimal;


/**
 * Created by zhong on 2020-8-19.
 */
@Data
public class TimeDepositEntity {
    String accountID;

    String pwd;

    BigDecimal amount;

    int month;
}
