package com.imbus.bank.trunkModule.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-9-12.
 */
@Data
public class TrunkBo {
    private int id;

    private int agencyID;

    private int tellerID;

    private BigDecimal cash;

    private int number;
}
