package com.imbus.bank.rateModule.service;

import com.imbus.bank.rateModule.bo.TimeRateBo;
import com.imbus.bank.rateModule.bo.LoanRateBo;
import com.imbus.bank.rateModule.entity.TimeRateEntity;
import com.imbus.bank.rateModule.entity.LoanRateEntity;
import com.imbus.bank.rateModule.type.RateResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-9-7.
 */
public interface IRate {
    // 存款

    List<TimeRateBo> getPersonalTimeRate();

    List<TimeRateBo> getPublicTimeRate();

    List<LoanRateBo> getPersonalLoanRate();

    List<LoanRateBo> getPublicLoanRate();

    RateResult addPersonalTimeRate(TimeRateEntity timeRateEntity);

    RateResult editPersonalTimeRate(int id, TimeRateEntity timeRateEntity);

    RateResult deletePersonalTimeRate(int id);

    RateResult addPublicTimeRate(TimeRateEntity timeRateEntity);

    RateResult editPublicTimeRate(int id, TimeRateEntity timeRateEntity);

    RateResult deletePublicTimeRate(int id);

    BigDecimal getPersonalDepositRate();

    RateResult setPersonalDepositRate(BigDecimal rate);

    BigDecimal getPublicDepositRate();

    RateResult setPublicDepositRate(BigDecimal rate);

    // 贷款

    RateResult addPersonalLoanRate(LoanRateEntity loanRateEntity);

    RateResult deletePersonalLoanRate(int id);

    RateResult addPublicLoanRate(LoanRateEntity loanRateEntity);

    RateResult deletePublicLoanRate(int id);

}
