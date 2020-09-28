package com.imbus.bank.loanModule.service;

import com.imbus.bank.loanModule.bo.LoanBo;
import com.imbus.bank.loanModule.bo.LoanSearchEntity;

import java.util.List;

/**
 * Created by zhong on 2020-8-29.
 */
public interface ILoanSearch {
    int getLoanPage(LoanSearchEntity loanSearchEntity);

    List<LoanBo> getLoanList(LoanSearchEntity loanSearchEntity, int page);

    int getUnhandledLoanPage(LoanSearchEntity loanSearchEntity);

    List<LoanBo> getUnhandledLoanList(LoanSearchEntity loanSearchEntity, int page);

    LoanBo getLoanDetail(int loanID);

    List<LoanBo> getAccountLoan(String accountID);
}
