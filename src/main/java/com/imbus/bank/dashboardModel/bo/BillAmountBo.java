package com.imbus.bank.dashboardModel.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhong on 2020-11-14.
 * 现金流水账金额
 */
@Data
public class BillAmountBo {
    private BigDecimal amount;

    private String billDate;
}
