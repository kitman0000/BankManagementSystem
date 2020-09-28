package com.imbus.bank.trunkModule.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-9-11.
 */
@Mapper
@Repository
public interface TrunkCashDao {
    void addTrunkCash(@Param("id") int id, @Param("amount") BigDecimal amount);

    void removeTrunkCash(@Param("id") int id, @Param("amount") BigDecimal amount);

    BigDecimal getTrunkCash(int id);

    int getTellerTrunkID(int tellerID);
}
