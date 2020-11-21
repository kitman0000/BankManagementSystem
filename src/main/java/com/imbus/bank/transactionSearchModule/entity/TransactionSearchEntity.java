package com.imbus.bank.transactionSearchModule.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhong on 2020-9-8.
 */
@Data
public class TransactionSearchEntity {
    private String accountID;

    private boolean showCash;

    private boolean showAssets;

    private boolean showDebt;

    private String startTime;

    private String endTime;
}
