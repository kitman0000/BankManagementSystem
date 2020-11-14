package com.imbus.bank.rateModule.service.impl;

import com.imbus.bank.rateModule.bo.TimeRateBo;
import com.imbus.bank.rateModule.bo.LoanRateBo;
import com.imbus.bank.rateModule.dao.RateDao;
import com.imbus.bank.rateModule.entity.TimeRateEntity;
import com.imbus.bank.rateModule.entity.LoanRateEntity;
import com.imbus.bank.rateModule.service.IRate;
import com.imbus.bank.rateModule.type.RateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-9-7.
 */

@Service
public class RateImpl implements IRate {
    @Autowired
    private RateDao rateDao;

    @Override
    public List<TimeRateBo> getPersonalTimeRate() {
        return rateDao.getPersonalTimeRate();
    }

    @Override
    public List<TimeRateBo> getPublicTimeRate() {
        return rateDao.getPublicTimeRate();
    }

    @Override
    public List<LoanRateBo> getPersonalLoanRate() {
        return rateDao.getPersonalLoanRate();
    }

    @Override
    public List<LoanRateBo> getPublicLoanRate() {
        return rateDao.getPublicLoanRate();
    }

    @Override
    public RateResult addPersonalTimeRate(TimeRateEntity timeRateEntity) {
        rateDao.addPersonalTimeRate(timeRateEntity);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult editPersonalTimeRate(int id, TimeRateEntity timeRateEntity) {
        rateDao.editPersonalTimeRate(id, timeRateEntity);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult deletePersonalTimeRate(int id) {
        rateDao.deletePersonalTimeRate(id);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult addPublicTimeRate(TimeRateEntity timeRateEntity) {
        rateDao.addPublicTimeRate(timeRateEntity);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult editPublicTimeRate(int id, TimeRateEntity timeRateEntity) {
        rateDao.editPublicTimeRate(id, timeRateEntity);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult deletePublicTimeRate(int id) {
        rateDao.deletePublicTimeRate(id);
        return RateResult.SUCCESS;
    }

    @Override
    public BigDecimal getPersonalDepositRate() {
        return rateDao.getPersonalDepositRate();
    }

    @Override
    public RateResult setPersonalDepositRate(BigDecimal rate) {
        rateDao.setPersonalDepositRate(rate);
        return RateResult.SUCCESS;
    }

    @Override
    public BigDecimal getPublicDepositRate() {
        return rateDao.getPublicDepositRate();
    }

    @Override
    public RateResult setPublicDepositRate(BigDecimal rate) {
        rateDao.setPublicDepositRate(rate);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult addPersonalLoanRate(LoanRateEntity loanRateEntity) {
        rateDao.addPersonalLoanRate(loanRateEntity);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult deletePersonalLoanRate(int id) {
        rateDao.deletePersonalLoanRate(id);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult addPublicLoanRate(LoanRateEntity loanRateEntity) {
        rateDao.addPublicLoanRate(loanRateEntity);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult deletePublicLoanRate(int id) {
        rateDao.deletePublicLoanRate(id);
        return RateResult.SUCCESS;
    }
}
