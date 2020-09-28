package com.imbus.bank.transactionSearchModule.service;

import com.imbus.bank.transactionSearchModule.bo.TransactionBo;
import com.imbus.bank.transactionSearchModule.entity.TransactionSearchEntity;

import java.util.List;

/**
 * Created by zhong on 2020-9-8.
 */
public interface ITransaction {
    List<TransactionBo> getTransaction(TransactionSearchEntity searchEntity);
}
