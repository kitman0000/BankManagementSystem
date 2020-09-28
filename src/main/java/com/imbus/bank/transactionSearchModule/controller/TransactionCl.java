package com.imbus.bank.transactionSearchModule.controller;

import com.imbus.bank.transactionSearchModule.bo.TransactionBo;
import com.imbus.bank.transactionSearchModule.entity.TransactionSearchEntity;
import com.imbus.bank.transactionSearchModule.service.ITransaction;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-9-8.
 */
@Api(description = "交易流水查询接口")
@RestController
public class TransactionCl{
    @Autowired
    private ITransaction transaction;

    @RequestMapping(value = "/api/transaction/search",method = RequestMethod.GET)
    public List<TransactionBo> getTransaction(TransactionSearchEntity searchEntity) {
        return transaction.getTransaction(searchEntity);
    }
}
