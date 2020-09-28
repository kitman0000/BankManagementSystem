package com.imbus.bank.rateModule.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-9-7.
 */
@Data
public class DepositRateBo {
    private int id;

    private int month;

    private BigDecimal rate;
}
