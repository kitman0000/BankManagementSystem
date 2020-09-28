package com.imbus.bank.nettingModule.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-9-28.
 */
@Data
public class NettingWarningBo {
    private int id;

    private String target;

    private BigDecimal theoryAmount;

    private BigDecimal actualAmount;

    private String nettingSign;
}
