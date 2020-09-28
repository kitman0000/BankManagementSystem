package com.imbus.bank.publicServiceModule.service;

import com.imbus.bank.componet.Entity.TimeDepositEntity;
import com.imbus.bank.componet.Entity.TimeToDemandEntity;
import com.imbus.bank.componet.Entity.TimeWithdrawEntity;
import com.imbus.bank.componet.bo.TimeWithdrawBo;
import com.imbus.bank.componet.bo.TimeDepositItemBo;
import com.imbus.bank.componet.type.TimeDepositResult;
import com.imbus.bank.componet.bo.TimeToDemandBo;

import java.util.List;

/**
 * Created by zhong on 2020-8-20.
 */
public interface IPublicTime {
    TimeDepositResult timeDeposit(TimeDepositEntity timeDepositEntity);

    TimeWithdrawBo timeWithdraw(TimeWithdrawEntity timeWithdrawEntity);

    List<TimeDepositItemBo> getTimeDeposit(String accountID);

    TimeToDemandBo timeToDemand(TimeToDemandEntity timeToDemandEntity);
}
