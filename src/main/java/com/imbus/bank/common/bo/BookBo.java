package com.imbus.bank.common.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhong on 2020-8-31.
 * 账单类
 */
@Data
public class BookBo {
    private int id;

    private BigDecimal amount;

    private Date transactDatetime;

    private String target;

    private String remark;

    private int agencyID;

    private int tellerID;

    private int tag;

    private int cashObject;
}
