package com.imbus.bank.loanModule.dao;

import com.imbus.bank.loanModule.bo.LoanBo;
import com.imbus.bank.loanModule.bo.LoanSearchEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by zhong on 2020-8-29.
 */
@Repository
@Mapper
public interface LoanSearchDao {
    int getLoanPage(@Param("loanSearchEntity") LoanSearchEntity loanSearchEntity);

    List<LoanBo> getLoanList(@Param("loanSearchEntity") LoanSearchEntity loanSearchEntity, @Param("startRow") int startRow, @Param("rowsOnePage") int rowsOnePage);

    int getUnhandledLoanPage(LoanSearchEntity loanSearchEntity);

    List<LoanBo> getUnhandledLoanList(LoanSearchEntity loanSearchEntity);

    LoanBo getLoanDetail(int id);

    List<Integer> getUnpaidLoanByDate(@Param("startScheduledPayment") Date startScheduledPayment, @Param("endScheduledPayment") Date endScheduledPayment);

    List<LoanBo> getAccountLoanList(@Param("accountID") String accountID);
}
