package com.imbus.bank.componet;

import com.imbus.bank.componet.Entity.DemandDepositEntity;
import com.imbus.bank.componet.Entity.DemandToTimeEntity;
import com.imbus.bank.componet.Entity.DemandWithdrawEntity;
import com.imbus.bank.componet.type.DemandDepositResult;
import com.imbus.bank.componet.type.DemandToTimeResult;
import com.imbus.bank.componet.type.DemandWithdrawResult;

/**
 * Created by zhong on 2020-8-19.
 * 活期存款业务接口，所有活期存款业务Control层必须实现此接口
 */
public interface IDemandService {
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
