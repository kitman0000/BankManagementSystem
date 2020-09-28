package com.imbus.bank.publicServiceModule.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-8-20.
 */
@Mapper
@Repository
public interface PublicRateDao {
    BigDecimal getRate(int month);
}
