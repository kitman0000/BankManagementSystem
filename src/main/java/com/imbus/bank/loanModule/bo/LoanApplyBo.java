package com.imbus.bank.loanModule.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhong on 2020-8-29.
 */
@Data
public class LoanApplyBo {
    private int id;

    private String accountID;

    private BigDecimal amount;

    private Date loanDate;

    private BigDecimal rate;

    private Date scheduledPayment;

    private int guaranteeType;

    private String guaranteeInfo;

    private String usage;

    private int handleStatus;

    private int handleUser;

    private int month;
}
