package com.imbus.bank.loanModule.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhong on 2020-8-29.
 */
@Data
public class LoanBo {
    private int id;

    private String account;

    private BigDecimal amount;

    private Date loanDate;

    private BigDecimal rate;

    private int repaymentStatus;

    private Date scheduledPayment;

    private Date actualPayment;

    private int guaranteeType;

    private String guaranteeInfo;

    private String usage;
}
