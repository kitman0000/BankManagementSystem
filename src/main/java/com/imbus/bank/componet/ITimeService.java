package com.imbus.bank.componet;

import com.imbus.bank.componet.Entity.TimeDepositEntity;
import com.imbus.bank.componet.Entity.TimeToDemandEntity;
import com.imbus.bank.componet.Entity.TimeWithdrawEntity;
import com.imbus.bank.componet.bo.TimeDepositItemBo;
import com.imbus.bank.componet.type.TimeDepositResult;
import com.imbus.bank.componet.bo.TimeToDemandBo;
import com.imbus.bank.componet.bo.TimeWithdrawBo;

import java.util.List;

/**
 * Created by zhong on 2020-8-19.
 * 定期存款业务接口，所有定期存款业务Control层必须实现此接口
 */
public interface ITimeService {
    /***
     * 定期存款
     */
    TimeDepositResult timeDeposit(TimeDepositEntity timeDepositEntity);

    /**
     * 定期取款
     */
    TimeWithdrawBo timeWithdraw(TimeWithdrawEntity timeWithdrawEntity);

    /**
     * 获取账户下的所有未取出定期存款
     * @param accountID 银行卡号
     * @return 存款列表
     */
    List<TimeDepositItemBo> getTimeDeposit(String accountID);

    /**
     * 定期转活期
     */
    TimeToDemandBo timeToDemand(TimeToDemandEntity timeToDemandEntity);
}
