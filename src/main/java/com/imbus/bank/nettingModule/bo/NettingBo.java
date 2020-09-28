package com.imbus.bank.nettingModule.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhong on 2020-9-28.
 */
@Data
public class NettingBo {
    private int id;

    private Date netDate;

    private boolean result;

    private int netCount;

    private BigDecimal amount;

    private Date startTime;

    private Date endTime;

    private String sign;
}
