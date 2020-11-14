package com.imbus.bank.personalServiceModule.controller;

import com.imbus.bank.componet.Entity.TimeDepositEntity;
import com.imbus.bank.componet.Entity.TimeToDemandEntity;
import com.imbus.bank.componet.Entity.TimeWithdrawEntity;
import com.imbus.bank.componet.ITimeService;
import com.imbus.bank.componet.bo.TimeWithdrawBo;
import com.imbus.bank.componet.bo.TimeDepositItemBo;
import com.imbus.bank.componet.type.TimeDepositResult;
import com.imbus.bank.componet.bo.TimeToDemandBo;
import com.imbus.bank.personalServiceModule.service.IPersonalTime;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "个人定期接口")
@RestController
public class PersonalTimeCl implements ITimeService {
    @Autowired
    private IPersonalTime personalTime;

    @Transactional
    @Override
    @RequiresPermissions("personal:time:deposit")
    @RequestMapping(value = "/api/personalTime/deposit",method = RequestMethod.POST)
    public TimeDepositResult timeDeposit(TimeDepositEntity timeDepositEntity) {
        return personalTime.timeDeposit(timeDepositEntity);
    }

    @Transactional
    @Override
    @RequiresPermissions("personal:time:withdraw")
    @RequestMapping(value = "/api/personalTime/withdraw",method = RequestMethod.POST)
    public TimeWithdrawBo timeWithdraw(TimeWithdrawEntity timeWithdrawEntity) {
        return personalTime.timeWithdraw(timeWithdrawEntity);
    }

    @Transactional
    @Override
    @RequestMapping(value = "/api/personalTime/deposit",method = RequestMethod.GET)
    public List<TimeDepositItemBo> getTimeDeposit(String accountID) {
        return personalTime.getTimeDeposit(accountID);
    }

    @Transactional
    @Override
    @RequestMapping(value = "/api/personalTime/demand",method = RequestMethod.POST)
    public TimeToDemandBo timeToDemand(TimeToDemandEntity timeToDemandEntity) {
        return personalTime.timeToDemand(timeToDemandEntity);
    }
}
