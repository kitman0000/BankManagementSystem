package com.imbus.bank.rateModule.dao;

import com.imbus.bank.rateModule.bo.DepositRateBo;
import com.imbus.bank.rateModule.bo.LoanRateBo;
import com.imbus.bank.rateModule.entity.DepositRateEntity;
import com.imbus.bank.rateModule.entity.LoanRateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhong on 2020-9-7.
 */
@Mapper
@Repository
public interface RateDao {
    List<DepositRateBo> getPersonalDepositRate();

    List<DepositRateBo> getPublicDepositRate();

    List<LoanRateBo> getPersonalLoanRate();

    List<LoanRateBo> getPublicLoanRate();

    void addPersonalDepositRate(@Param("depositRateEntity") DepositRateEntity depositRateEntity);

    void editPersonalDepositRate(@Param("id") int id, @Param("depositRateEntity") DepositRateEntity depositRateEntity);

    void deletePersonalDepositRate(int id);

    void addPublicDepositRate(@Param("depositRateEntity") DepositRateEntity depositRateEntity);

    void editPublicDepositRate(@Param("id") int id, @Param("depositRateEntity") DepositRateEntity depositRateEntity);

    void deletePublicDepositRate(int id);

    void addPersonalLoanRate(LoanRateEntity loanRateEntity);

    void deletePersonalLoanRate(int id);

    void addPublicLoanRate(LoanRateEntity loanRateEntity);

    void deletePublicLoanRate(int id);
}
