package com.imbus.bank.componet.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhong on 2020-8-23.
 */
@Data
public class TimeDepositItemBo {
    int id;

    String accountID;

    Date depositDate;

    BigDecimal rate;

    BigDecimal amount;

    int withDrawStatus;

    Date scheduledWithdrawDate;

}
