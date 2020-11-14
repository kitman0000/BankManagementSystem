package com.imbus.bank.rateModule.controller;

import com.imbus.bank.rateModule.bo.TimeRateBo;
import com.imbus.bank.rateModule.bo.LoanRateBo;
import com.imbus.bank.rateModule.entity.TimeRateEntity;
import com.imbus.bank.rateModule.entity.LoanRateEntity;
import com.imbus.bank.rateModule.service.IRate;
import com.imbus.bank.rateModule.type.RateResult;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-9-7.
 */
@Api(description = "利率接口")
@RestController
public class RateCl{
    @Autowired
    private IRate rate;

    @RequiresPermissions("rate:get")
    @RequestMapping(value = "/api/rate/personalTimeRate",method = RequestMethod.GET)
    public List<TimeRateBo> getPersonalTimeRate() {
        return rate.getPersonalTimeRate();
    }

    @RequiresPermissions("rate:get")
    @RequestMapping(value = "/api/rate/publicTimeRate",method = RequestMethod.GET)
    public List<TimeRateBo> getPublicTimeRate() {
        return rate.getPublicTimeRate();
    }

    @RequiresPermissions("rate:get")
    @RequestMapping(value = "/api/rate/personalLoanRate",method = RequestMethod.GET)
    public List<LoanRateBo> getPersonalLoanRate() {
        return rate.getPersonalLoanRate();
    }

    @RequiresPermissions("rate:get")
    @RequestMapping(value = "/api/rate/publicLoanRate",method = RequestMethod.GET)
    public List<LoanRateBo> getPublicLoanRate() {
        return rate.getPublicLoanRate();
    }

    @RequiresPermissions("rate:manage")
    @RequestMapping(value = "/api/rate/personalTimeRate",method = RequestMethod.POST)
    public RateResult addPersonalTimeRate(TimeRateEntity timeRateEntity) {
        return rate.addPersonalTimeRate(timeRateEntity);
    }

    @RequiresPermissions("rate:manage")
    @RequestMapping(value = "/api/rate/personalTimeRate",method = RequestMethod.PUT)
    public RateResult editPersonalTimeRate(int id, TimeRateEntity timeRateEntity) {
        return rate.editPersonalTimeRate(id, timeRateEntity);
    }

    @RequiresPermissions("rate:manage")
    @RequestMapping(value = "/api/rate/personalTimeRate",method = RequestMethod.DELETE)
    public RateResult deletePersonalTimeRate(int id) {
        return rate.deletePersonalTimeRate(id);
    }

    @RequiresPermissions("rate:manage")
    @RequestMapping(value = "/api/rate/publicTimeRate",method = RequestMethod.POST)
    public RateResult addPublicTimeRate(TimeRateEntity timeRateEntity) {
        return rate.addPublicTimeRate(timeRateEntity);
    }

    @RequiresPermissions("rate:manage")
    @RequestMapping(value = "/api/rate/publicTimeRate",method = RequestMethod.PUT)
    public RateResult editPublicTimeRate(int id, TimeRateEntity timeRateEntity) {
        return rate.editPublicTimeRate(id, timeRateEntity);
    }

    @RequiresPermissions("rate:manage")
    @RequestMapping(value = "/api/rate/publicTimeRate",method = RequestMethod.DELETE)
    public RateResult deletePublicTimeRate(int id) {
        return rate.deletePublicTimeRate(id);
    }

    @RequiresPermissions("rate:manage")
    @RequestMapping(value = "/api/rate/personalLoanRate",method = RequestMethod.POST)
    public RateResult addPersonalLoanRate(LoanRateEntity loanRateEntity) {
        return rate.addPersonalLoanRate(loanRateEntity);
    }

    @RequiresPermissions("rate:manage")
    @RequestMapping(value = "/api/rate/personalLoanRate",method = RequestMethod.DELETE)
    public RateResult deletePersonalLoanRate(int id) {
        return rate.deletePersonalLoanRate(id);
    }

    @RequiresPermissions("rate:manage")
    @RequestMapping(value = "/api/rate/publicLoanRate",method = RequestMethod.POST)
    public RateResult addPublicLoanRate(LoanRateEntity loanRateEntity) {
        return rate.addPublicLoanRate(loanRateEntity);
    }

    @RequiresPermissions("rate:manage")
    @RequestMapping(value = "/api/rate/publicLoanRate",method = RequestMethod.DELETE)
    public RateResult deletePublicLoanRate(int id) {
        return rate.deletePublicLoanRate(id);
    }

    @RequiresPermissions("rate:get")
    @RequestMapping(value = "/api/rate/personalDepositRate",method = RequestMethod.GET)
    public BigDecimal getPersonalDepositRate(){
        return rate.getPersonalDepositRate();
    }

    @RequiresPermissions("rate:manage")
    @RequestMapping(value = "/api/rate/personalDepositRate",method = RequestMethod.POST)
    public RateResult setPersonalDepositRate(BigDecimal rateValue){
        return rate.setPersonalDepositRate(rateValue);
    }

    @RequiresPermissions("rate:get")
    @RequestMapping(value = "/api/rate/publicDepositRate",method = RequestMethod.GET)
    public BigDecimal getPublicDepositRate(){
        return rate.getPublicDepositRate();
    }

    @RequiresPermissions("rate:manage")
    @RequestMapping(value = "/api/rate/publicDepositRate",method = RequestMethod.POST)
    public RateResult setPublicDepositRate(BigDecimal rateValue){
        return rate.setPublicDepositRate(rateValue);
    }

}
