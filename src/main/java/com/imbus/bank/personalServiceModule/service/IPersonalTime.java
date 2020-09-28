package com.imbus.bank.personalServiceModule.service;

import com.imbus.bank.componet.Entity.TimeDepositEntity;
import com.imbus.bank.componet.Entity.TimeToDemandEntity;
import com.imbus.bank.componet.Entity.TimeWithdrawEntity;
import com.imbus.bank.componet.bo.TimeWithdrawBo;
import com.imbus.bank.componet.bo.TimeDepositItemBo;
import com.imbus.bank.componet.type.TimeDepositResult;
import com.imbus.bank.componet.bo.TimeToDemandBo;

import java.util.List;

public interface IPersonalTime {
    TimeDepositResult timeDeposit(TimeDepositEntity timeDepositEntity);

    TimeWithdrawBo timeWithdraw(TimeWithdrawEntity timeWithdrawEntity);

    List<TimeDepositItemBo> getTimeDeposit(String accountID);

    TimeToDemandBo timeToDemand(TimeToDemandEntity timeToDemandEntity);
}
