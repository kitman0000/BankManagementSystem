package com.imbus.bank.componet.Entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-8-19.
 * 活期存款entity
 */
@Data
public class DemandDepositEntity {
    String accountID;

    BigDecimal amount;

    String pwd;
}
