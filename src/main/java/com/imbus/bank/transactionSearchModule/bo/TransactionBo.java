package com.imbus.bank.transactionSearchModule.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhong on 2020-9-8.
 */
@Data
public class TransactionBo {
    private int id;

    private BigDecimal amount;

    private Date transactDatetime;

    private String target;

    private String remark;

    private String agencyName;

    private String tellerName;

    private int tag;

    private int type; // 显示资金端或现金端 1:资金端 2：现金端
}
