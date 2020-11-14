package com.imbus.bank.rateModule.dao;

import com.imbus.bank.rateModule.bo.TimeRateBo;
import com.imbus.bank.rateModule.bo.LoanRateBo;
import com.imbus.bank.rateModule.entity.TimeRateEntity;
import com.imbus.bank.rateModule.entity.LoanRateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-9-7.
 */
@Mapper
@Repository
public interface RateDao {
    List<TimeRateBo> getPersonalTimeRate();

    List<TimeRateBo> getPublicTimeRate();

    List<LoanRateBo> getPersonalLoanRate();

    List<LoanRateBo> getPublicLoanRate();

    void addPersonalTimeRate(@Param("timeRateEntity") TimeRateEntity timeRateEntity);

    void editPersonalTimeRate(@Param("id") int id, @Param("timeRateEntity") TimeRateEntity timeRateEntity);

    void deletePersonalTimeRate(int id);

    void addPublicTimeRate(@Param("timeRateEntity") TimeRateEntity timeRateEntity);

    void editPublicTimeRate(@Param("id") int id, @Param("timeRateEntity") TimeRateEntity timeRateEntity);

    void deletePublicTimeRate(int id);

    void addPersonalLoanRate(@Param("loanRateEntity") LoanRateEntity loanRateEntity);

    void deletePersonalLoanRate(int id);

    void addPublicLoanRate(@Param("loanRateEntity") LoanRateEntity loanRateEntity);

    void deletePublicLoanRate(int id);

    BigDecimal getPersonalDepositRate();

    void setPersonalDepositRate(BigDecimal rate);

    BigDecimal getPublicDepositRate();

    void setPublicDepositRate(BigDecimal rate);
}
