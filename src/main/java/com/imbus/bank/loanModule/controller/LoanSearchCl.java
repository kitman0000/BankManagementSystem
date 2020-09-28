package com.imbus.bank.loanModule.controller;

import com.imbus.bank.loanModule.bo.LoanBo;
import com.imbus.bank.loanModule.bo.LoanSearchEntity;
import com.imbus.bank.loanModule.service.ILoanSearch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-8-29.
 */
@Api(description = "贷款检索接口")
@RestController
public class LoanSearchCl {

    @Autowired
    private ILoanSearch loanSearch;

    @ApiOperation(value = "获取贷款页数")
    @RequestMapping(value = "/api/loanSearch/loanPage",method = RequestMethod.GET)
    public int getLoanPage(LoanSearchEntity loanSearchEntity){
        return loanSearch.getLoanPage(loanSearchEntity);
    }

    @ApiOperation(value = "获取贷款列表")
    @RequestMapping(value = "/api/loanSearch/loan",method = RequestMethod.GET)
    public List<LoanBo> getLoanList(LoanSearchEntity loanSearchEntity,int page) {
        return loanSearch.getLoanList(loanSearchEntity,page);
    }

    @ApiOperation(value = "获取未处理的贷款页数")
    @RequestMapping(value = "/api/loanSearch/unhandledLoanPage",method = RequestMethod.GET)
    public int getUnhandledLoanPage(LoanSearchEntity loanSearchEntity){
        return loanSearch.getUnhandledLoanPage(loanSearchEntity);
    }

    @ApiOperation(value = "获取未处理的贷款列表")
    @RequestMapping(value = "/api/loanSearch/unhandledLoan",method = RequestMethod.GET)
    public List<LoanBo> getUnhandledLoanList(LoanSearchEntity loanSearchEntity,int page){
        return loanSearch.getUnhandledLoanList(loanSearchEntity,page);
    }

    @ApiOperation(value = "获取账户的贷款")
    @RequestMapping(value = "/api/loanSearch/accountLoan",method = RequestMethod.GET)
    public List<LoanBo> getAccountLoan(String accountID){
        return loanSearch.getAccountLoan(accountID);
    }

}
