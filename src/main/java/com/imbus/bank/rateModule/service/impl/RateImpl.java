package com.imbus.bank.rateModule.service.impl;

import com.imbus.bank.rateModule.bo.DepositRateBo;
import com.imbus.bank.rateModule.bo.LoanRateBo;
import com.imbus.bank.rateModule.dao.RateDao;
import com.imbus.bank.rateModule.entity.DepositRateEntity;
import com.imbus.bank.rateModule.entity.LoanRateEntity;
import com.imbus.bank.rateModule.service.IRate;
import com.imbus.bank.rateModule.type.RateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2020-9-7.
 */

@Service
public class RateImpl implements IRate {
    @Autowired
    private RateDao rateDao;

    @Override
    public List<DepositRateBo> getPersonalDepositRate() {
        return rateDao.getPersonalDepositRate();
    }

    @Override
    public List<DepositRateBo> getPublicDepositRate() {
        return rateDao.getPublicDepositRate();
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
    public RateResult addPersonalDepositRate(DepositRateEntity depositRateEntity) {
        rateDao.addPersonalDepositRate(depositRateEntity);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult editPersonalDepositRate(int id, DepositRateEntity depositRateEntity) {
        rateDao.editPersonalDepositRate(id,depositRateEntity);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult deletePersonalDepositRate(int id) {
        rateDao.deletePersonalDepositRate(id);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult addPublicDepositRate(DepositRateEntity depositRateEntity) {
        rateDao.addPublicDepositRate(depositRateEntity);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult editPublicDepositRate(int id, DepositRateEntity depositRateEntity) {
        rateDao.editPublicDepositRate(id,depositRateEntity);
        return RateResult.SUCCESS;
    }

    @Override
    public RateResult deletePublicDepositRate(int id) {
        rateDao.deletePublicDepositRate(id);
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
