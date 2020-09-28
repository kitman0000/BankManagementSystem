package com.imbus.bank.common.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-8-31.
 */
@Mapper
@Repository
public interface BankConfigDao {
    BigDecimal getPublicDemandRate();

    BigDecimal getPersonalDemandRate();

    BigDecimal getLastAsset();

    BigDecimal getLastDebt();

    void setLastAsset(BigDecimal lastAsset);

    void setLastDebt(BigDecimal lastDebt);

    void setDepositReserveRate(BigDecimal value);

    BigDecimal getDepositReserveRate();

    void setDepositReserve(BigDecimal value);

    BigDecimal getDepositReserve();

    BigDecimal getPublicTotalBalance();

    BigDecimal getPersonalTotalBalance();

    BigDecimal getDepositTotal();
}
