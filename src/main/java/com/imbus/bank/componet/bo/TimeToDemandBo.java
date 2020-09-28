package com.imbus.bank.componet.bo;

import com.imbus.bank.publicServiceModule.type.TimeWithdrawResult;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-8-19.
 */
@Data
public class TimeToDemandBo {
    private TimeWithdrawResult result;

    // 取出类型 1：到期支取 2：提前支取
    private int withdrawType;

    // 金额
    private BigDecimal amount;
}
