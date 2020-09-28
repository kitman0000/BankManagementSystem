package com.imbus.bank.loanModule.service;

import com.imbus.bank.loanModule.entity.LoanEntity;
import com.imbus.bank.loanModule.type.HandleLoanResult;
import com.imbus.bank.loanModule.type.LoanResult;
import com.imbus.bank.loanModule.type.RepaymentResult;

/**
 * Created by zhong on 2020-8-29.
 */
public interface ILoan {
    LoanResult requestLoan(LoanEntity loanEntity);

    HandleLoanResult handleLoanApply(int id, int result);

    RepaymentResult repayLoan(int id,String pwd);
}
