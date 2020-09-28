package com.imbus.bank.rateModule.controller;

import com.imbus.bank.rateModule.bo.DepositRateBo;
import com.imbus.bank.rateModule.bo.LoanRateBo;
import com.imbus.bank.rateModule.entity.DepositRateEntity;
import com.imbus.bank.rateModule.entity.LoanRateEntity;
import com.imbus.bank.rateModule.service.IRate;
import com.imbus.bank.rateModule.type.RateResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-9-7.
 */
@Api(description = "利率接口")
@RestController
public class RateCl{
    @Autowired
    private IRate rate;

    @RequestMapping(value = "/api/rate/personalDepositRate",method = RequestMethod.GET)
    public List<DepositRateBo> getPersonalDepositRate() {
        return rate.getPersonalDepositRate();
    }

    @RequestMapping(value = "/api/rate/publicDepositRate",method = RequestMethod.GET)
    public List<DepositRateBo> getPublicDepositRate() {
        return rate.getPublicDepositRate();
    }

    @RequestMapping(value = "/api/rate/personalLoanRate",method = RequestMethod.GET)
    public List<LoanRateBo> getPersonalLoanRate() {
        return rate.getPersonalLoanRate();
    }

    @RequestMapping(value = "/api/rate/publicLoanRate",method = RequestMethod.GET)
    public List<LoanRateBo> getPublicLoanRate() {
        return rate.getPublicLoanRate();
    }

    @RequestMapping(value = "/api/rate/personalDepositRate",method = RequestMethod.POST)
    public RateResult addPersonalDepositRate(DepositRateEntity depositRateEntity) {
        return rate.addPersonalDepositRate(depositRateEntity);
    }

    @RequestMapping(value = "/api/rate/personalDepositRate",method = RequestMethod.PUT)
    public RateResult editPersonalDepositRate(int id, DepositRateEntity depositRateEntity) {
        return rate.editPersonalDepositRate(id,depositRateEntity);
    }

    @RequestMapping(value = "/api/rate/personalDepositRate",method = RequestMethod.DELETE)
    public RateResult deletePersonalDepositRate(int id) {
        return rate.deletePersonalDepositRate(id);
    }

    @RequestMapping(value = "/api/rate/publicDepositRate",method = RequestMethod.POST)
    public RateResult addPublicDepositRate(DepositRateEntity depositRateEntity) {
        return rate.addPublicDepositRate(depositRateEntity);
    }

    @RequestMapping(value = "/api/rate/publicDepositRate",method = RequestMethod.PUT)
    public RateResult editPublicDepositRate(int id, DepositRateEntity depositRateEntity) {
        return rate.editPublicDepositRate(id,depositRateEntity);
    }

    @RequestMapping(value = "/api/rate/publicDepositRate",method = RequestMethod.DELETE)
    public RateResult deletePublicDepositRate(int id) {
        return rate.deletePublicDepositRate(id);
    }

    @RequestMapping(value = "/api/rate/personalLoanRate",method = RequestMethod.POST)
    public RateResult addPersonalLoanRate(LoanRateEntity loanRateEntity) {
        return rate.addPersonalLoanRate(loanRateEntity);
    }

    @RequestMapping(value = "/api/rate/personalLoanRate",method = RequestMethod.DELETE)
    public RateResult deletePersonalLoanRate(int id) {
        return rate.deletePersonalLoanRate(id);
    }

    @RequestMapping(value = "/api/rate/publicLoanRate",method = RequestMethod.POST)
    public RateResult addPublicLoanRate(LoanRateEntity loanRateEntity) {
        return rate.addPublicLoanRate(loanRateEntity);
    }

    @RequestMapping(value = "/api/rate/publicLoanRate",method = RequestMethod.DELETE)
    public RateResult deletePublicLoanRate(int id) {
        return rate.deletePublicLoanRate(id);
    }
}
