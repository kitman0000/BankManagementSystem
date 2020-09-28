package com.imbus.bank.transactionSearchModule.service.impl;

import com.imbus.bank.transactionSearchModule.bo.TransactionBo;
import com.imbus.bank.transactionSearchModule.dao.TransactionDao;
import com.imbus.bank.transactionSearchModule.entity.TransactionSearchEntity;
import com.imbus.bank.transactionSearchModule.service.ITransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhong on 2020-9-8.
 */
@Service
public class TransactionImpl implements ITransaction{
    @Autowired
    private TransactionDao transactionDao;

    @Override
    public List<TransactionBo> getTransaction(TransactionSearchEntity searchEntity) {
        List<TransactionBo> transactionList = null;

        // 如果请求查询资产或请求查询借贷
        if(searchEntity.isShowAssets() || searchEntity.isShowDebt()){
            transactionList = transactionDao.getFundTransactionList(searchEntity);
        }

        if(searchEntity.isShowCash()){
            List<TransactionBo> cashTransactionList = transactionDao.getCashTransactionList(searchEntity);

            List<TransactionBo> newList = new LinkedList<>();

            // 没有资金账单，直接返回现金账单
            if(transactionList == null || transactionList.size() == 0){
                return cashTransactionList;
            }

            // 没有现金账单，直接返回资金账单
            if(cashTransactionList == null || cashTransactionList.size() == 0){
                return transactionList;
            }

            int cashIndex = 0; // 遍历现金账单下标
            // 合并资金、现金账单
            for (TransactionBo transaction:transactionList) {
                if (cashIndex == cashTransactionList.size()) {
                    // 现金已经遍历完
                    newList.add(transaction);
                    continue;
                }

                if (transaction.getTransactDatetime().before(cashTransactionList.get(cashIndex).getTransactDatetime())) {
                    newList.add(transaction);
                } else {
                    newList.add(cashTransactionList.get(cashIndex));
                    cashIndex++;
                }
            }

            // 如果遍历结束，现金没有遍历完
            while (cashIndex != cashTransactionList.size()) {
                newList.add(cashTransactionList.get(cashIndex));
                cashIndex++;
            }

            transactionList = newList;
        }
        return transactionList;
    }
}
