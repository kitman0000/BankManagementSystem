package com.imbus.bank.timeDepositModule.dao;

import com.imbus.bank.componet.Entity.TimeDepositEntity;
import com.imbus.bank.timeDepositModule.bo.DepositBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by zhong on 2020-9-1.
 */
@Mapper
@Repository
public interface DepositDao {
    List<DepositBo> getDeposit(@Param("scheduledStartDate") Date scheduledStartDate, @Param("scheduledEndDate") Date scheduledEndDate);

    void addDeposit(@Param("deposit") DepositBo deposit);

    void updateWithDrawStatus(@Param("id") int id, @Param("withDrawStatus") int withDrawStatus);

    List<DepositBo> getAccountDeposit(@Param("accountID") String accountID);
}
