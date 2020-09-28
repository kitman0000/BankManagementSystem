package com.imbus.bank.loanModule.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-8-29.
 */
@Mapper
@Repository
public interface LoanRateDao {
    BigDecimal getPersonalRate(int month);

    BigDecimal getPublicRate(int month);
}
