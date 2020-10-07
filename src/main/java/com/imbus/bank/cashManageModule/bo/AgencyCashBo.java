package com.imbus.bank.cashManageModule.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-10-1.
 */
@Data
public class AgencyCashBo {
    private int id;

    private String name;

    private BigDecimal cash;
}
