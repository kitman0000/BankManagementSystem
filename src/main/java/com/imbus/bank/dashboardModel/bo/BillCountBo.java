package com.imbus.bank.dashboardModel.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-11-14.
 * 现金流水账数量
 */
@Data
public class BillCountBo {
    private BigDecimal netCount;

    private String billDate;
}
