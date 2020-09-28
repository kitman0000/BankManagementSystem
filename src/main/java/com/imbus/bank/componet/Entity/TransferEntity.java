package com.imbus.bank.componet.Entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-8-25.
 */
@Data
public class TransferEntity {
    private String account;

    private String pwd;

    private BigDecimal amount;

    private String targetAccount;
}
