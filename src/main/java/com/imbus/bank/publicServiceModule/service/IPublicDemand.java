package com.imbus.bank.publicServiceModule.service;

import com.imbus.bank.componet.Entity.DemandDepositEntity;
import com.imbus.bank.componet.Entity.DemandToTimeEntity;
import com.imbus.bank.componet.Entity.DemandWithdrawEntity;
import com.imbus.bank.componet.type.DemandDepositResult;
import com.imbus.bank.componet.type.DemandToTimeResult;
import com.imbus.bank.componet.type.DemandWithdrawResult;

/**
 * Created by zhong on 2020-8-19.
 */
public interface IPublicDemand {
    /**
     * 活期存款
     */
    DemandDepositResult demandDeposit(DemandDepositEntity demandDepositEntity);

    /**
     * 活期取款
     */
    DemandWithdrawResult demandWithdraw(DemandWithdrawEntity demandWithdrawEntity);

    /**
     * 活期转定期
     */
    DemandToTimeResult demandToTime(DemandToTimeEntity demandToTimeEntity);
}
