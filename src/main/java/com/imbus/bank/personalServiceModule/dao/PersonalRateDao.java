package com.imbus.bank.personalServiceModule.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Mapper
@Repository
public interface PersonalRateDao {
    BigDecimal getRate(int month);
}
