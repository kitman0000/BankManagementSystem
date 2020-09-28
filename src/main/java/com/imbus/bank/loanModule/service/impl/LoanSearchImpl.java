package com.imbus.bank.loanModule.service.impl;

import com.imbus.bank.loanModule.bo.LoanBo;
import com.imbus.bank.loanModule.bo.LoanSearchEntity;
import com.imbus.bank.loanModule.dao.LoanSearchDao;
import com.imbus.bank.loanModule.service.ILoanSearch;
import com.imbus.bank.utils.PageDivideUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2020-8-29.
 */
@Service
public class LoanSearchImpl implements ILoanSearch {
    private static final int ROWS_ONE_PAGE = 10;

    @Autowired
    private LoanSearchDao loanSearchDao;

    @Override
    public int getLoanPage(LoanSearchEntity loanSearchEntity) {
        int amount = loanSearchDao.getLoanPage(loanSearchEntity);
        return PageDivideUtil.getCountOfPages(amount,ROWS_ONE_PAGE);
    }

    @Override
    public List<LoanBo> getLoanList(LoanSearchEntity loanSearchEntity, int page) {
        int startRow = (page -1) * ROWS_ONE_PAGE;
        return loanSearchDao.getLoanList(loanSearchEntity,startRow,ROWS_ONE_PAGE);
    }

    @Override
    public int getUnhandledLoanPage(LoanSearchEntity loanSearchEntity) {
        loanSearchEntity.setRepaymentStatus(0);
        return getLoanPage(loanSearchEntity);
    }

    @Override
    public List<LoanBo> getUnhandledLoanList(LoanSearchEntity loanSearchEntity, int page) {
        loanSearchEntity.setRepaymentStatus(0);
        return getLoanList(loanSearchEntity,page);
    }

    @Override
    public LoanBo getLoanDetail(int loanID) {
        return loanSearchDao.getLoanDetail(loanID);
    }


    @Override
    public List<LoanBo> getAccountLoan(String accountID) {
        return loanSearchDao.getAccountLoanList(accountID);
    }

}
