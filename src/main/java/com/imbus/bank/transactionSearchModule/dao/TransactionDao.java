package com.imbus.bank.transactionSearchModule.dao;

import com.imbus.bank.transactionSearchModule.bo.TransactionBo;
import com.imbus.bank.transactionSearchModule.entity.TransactionSearchEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhong on 2020-9-8.
 */
@Repository
@Mapper
public interface TransactionDao {
    List<TransactionBo> getFundTransactionList(@Param("searchEntity") TransactionSearchEntity searchEntity);

    List<TransactionBo> getCashTransactionList(@Param("searchEntity") TransactionSearchEntity searchEntity);
}
