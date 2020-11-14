package com.imbus.bank.loanModule.controller;

import com.imbus.bank.loanModule.entity.LoanEntity;
import com.imbus.bank.loanModule.service.ILoan;
import com.imbus.bank.loanModule.type.HandleLoanResult;
import com.imbus.bank.loanModule.type.LoanResult;
import com.imbus.bank.loanModule.type.RepaymentResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2020-8-29.
 */
@Api(description = "贷款接口")
@RestController
public class LoanCl {

    @Autowired
    ILoan loan;

    @ApiOperation(value = "申请贷款")
    @Transactional
    @RequiresPermissions(value = "loan:apply")
    @RequestMapping(value = "/api/loan/request",method = RequestMethod.POST)
    public LoanResult requestLoan(LoanEntity loanEntity){
        return loan.requestLoan(loanEntity);
    }

    @RequiresPermissions("loan:handleLoan")
    @ApiOperation(value = "处理贷款")
    @Transactional
    @RequestMapping(value = "/api/loan/handleLoanApply",method = RequestMethod.POST)
    public HandleLoanResult handleLoanApply(int id, int result){
        return loan.handleLoanApply(id,result);
    }

    @ApiOperation(value = "还款")
    @Transactional
    @RequestMapping(value = "/api/loan/repay",method = RequestMethod.POST)
    public RepaymentResult repayLoan(int id,String pwd){
        return loan.repayLoan(id,pwd);
    }
}
