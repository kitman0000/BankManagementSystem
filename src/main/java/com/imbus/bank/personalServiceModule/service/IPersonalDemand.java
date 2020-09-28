package com.imbus.bank.personalServiceModule.service;

import com.imbus.bank.componet.Entity.DemandDepositEntity;
import com.imbus.bank.componet.Entity.DemandToTimeEntity;
import com.imbus.bank.componet.Entity.DemandWithdrawEntity;
import com.imbus.bank.componet.type.DemandDepositResult;
import com.imbus.bank.componet.type.DemandToTimeResult;
import com.imbus.bank.componet.type.DemandWithdrawResult;

public interface IPersonalDemand {
    //活期存款
    DemandDepositResult demandDeposit(DemandDepositEntity demandDepositEntity);

    //活期取款
    DemandWithdrawResult demandWithdraw(DemandWithdrawEntity demandWithdrawEntity);

    //活期转定期
    DemandToTimeResult demandToTime(DemandToTimeEntity demandToTimeEntity);
}
