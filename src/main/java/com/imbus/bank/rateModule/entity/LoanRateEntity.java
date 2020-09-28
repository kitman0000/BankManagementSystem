package com.imbus.bank.rateModule.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-9-8.
 */
@Data
public class LoanRateEntity {
    private int id;

    private int startMonth;

    private int endMonth;

    private BigDecimal rate;
}
