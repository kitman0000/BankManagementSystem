package com.imbus.bank.timeDepositModule.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhong on 2020-9-1.
 */
@Data
public class DepositBo {
    private int id;

    private String accountID;

    private Date depositDate;

    private BigDecimal rate;

    private BigDecimal amount;

    private int withDrawStatus;

    private Date scheduledWithdrawDate;

    private Date actualWithdrawDate;
}
