package com.imbus.bank.publicServiceModule.dao;

import com.imbus.bank.componet.bo.TimeDepositItemBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zhong on 2020-8-20.
 */
@Mapper
@Repository
public interface PublicTimeDepositDao {
    void addDeposit(@Param("accountID") String accountID, @Param("depositDate") Date depositDate,
                    @Param("rate") BigDecimal rate, @Param("amount") BigDecimal amount,
                    @Param("scheduledWithdrawDate") Date scheduledWithdrawDate);

    List<TimeDepositItemBo> getDepositList(@Param("accountID") String accountID);

    TimeDepositItemBo getDeposit(int id);

    void updateTimeDepositStatus(@Param("id") int id, @Param("status") int status);
}
