package com.imbus.bank.dashboardModel.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-11-14.
 */
@Data
public class AgencyCashBo {
    private BigDecimal cash;

    private String agencyName;
}
