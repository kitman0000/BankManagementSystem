package com.imbus.bank.loanModule.dao;

import com.imbus.bank.loanModule.bo.LoanApplyBo;
import com.imbus.bank.loanModule.entity.LoanEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-8-29.
 */
@Repository
@Mapper
public interface LoanDao {
    void addLoanApply(@Param("loanEntity") LoanEntity loanEntity);

    LoanApplyBo getLoanApplyDetail(int loanID);

    void handleLoanApply(@Param("id") int id, @Param("result") int result, @Param("handleUser") int handleUser);

    void addLoan(@Param("loanEntity") LoanApplyBo loanEntity);

    void updateLoanRepaymentStatus(@Param("id") int id, @Param("status") int status);

    BigDecimal getTotalLoan();
}
