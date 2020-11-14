package com.imbus.bank.dashboardModel.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-11-14.
 */
@Data
public class AssetDebtCashBo {
    private BigDecimal asset;

    private BigDecimal debt;

    private BigDecimal cash;
}
