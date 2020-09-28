package com.imbus.bank.componet.Entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-8-19.
 */
@Data
public class DemandWithdrawEntity {
    private String accountID;

    private BigDecimal amount;

    private String pwd;
}
