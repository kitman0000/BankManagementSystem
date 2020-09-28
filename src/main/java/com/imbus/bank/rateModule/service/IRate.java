package com.imbus.bank.rateModule.service;

import com.imbus.bank.rateModule.bo.DepositRateBo;
import com.imbus.bank.rateModule.bo.LoanRateBo;
import com.imbus.bank.rateModule.entity.DepositRateEntity;
import com.imbus.bank.rateModule.entity.LoanRateEntity;
import com.imbus.bank.rateModule.type.RateResult;

import java.util.List;

/**
 * Created by zhong on 2020-9-7.
 */
public interface IRate {
    List<DepositRateBo> getPersonalDepositRate();

    List<DepositRateBo> getPublicDepositRate();

    List<LoanRateBo> getPersonalLoanRate();

    List<LoanRateBo> getPublicLoanRate();

    RateResult addPersonalDepositRate(DepositRateEntity depositRateEntity);

    RateResult editPersonalDepositRate(int id,DepositRateEntity depositRateEntity);

    RateResult deletePersonalDepositRate(int id);

    RateResult addPublicDepositRate(DepositRateEntity depositRateEntity);

    RateResult editPublicDepositRate(int id,DepositRateEntity depositRateEntity);

    RateResult deletePublicDepositRate(int id);

    RateResult addPersonalLoanRate(LoanRateEntity loanRateEntity);

    RateResult deletePersonalLoanRate(int id);

    RateResult addPublicLoanRate(LoanRateEntity loanRateEntity);

    RateResult deletePublicLoanRate(int id);
}
