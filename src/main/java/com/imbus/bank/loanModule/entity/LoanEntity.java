package com.imbus.bank.loanModule.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhong on 2020-8-29.
 */
@Data
public class LoanEntity {
    private String pwd;

    private int type;

    private String accountID;

    private BigDecimal amount;

    private Date loanDate;

    private int month;

    private BigDecimal rate;

    private int repaymentStatus;

    private Date scheduledPayment;

    private Date actualPayment;

    private int guaranteeType;

    private String guaranteeInfo;

    private String usage;
}
