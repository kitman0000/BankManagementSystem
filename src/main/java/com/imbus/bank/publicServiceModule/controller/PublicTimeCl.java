package com.imbus.bank.publicServiceModule.controller;

import com.imbus.bank.componet.Entity.TimeDepositEntity;
import com.imbus.bank.componet.Entity.TimeToDemandEntity;
import com.imbus.bank.componet.Entity.TimeWithdrawEntity;
import com.imbus.bank.componet.ITimeService;
import com.imbus.bank.componet.bo.TimeWithdrawBo;
import com.imbus.bank.componet.bo.TimeDepositItemBo;
import com.imbus.bank.componet.type.TimeDepositResult;
import com.imbus.bank.componet.bo.TimeToDemandBo;
import com.imbus.bank.publicServiceModule.service.IPublicTime;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-8-20.
 * 对公定期业务Controller，实现ITimeService接口
 */

@Api(description = "对公定期业务接口")
@RestController
public class PublicTimeCl implements ITimeService {

    @Autowired
    private IPublicTime publicTime;

    @Transactional
    @Override
    @RequiresPermissions("public:time:deposit")
    @RequestMapping(value = "/api/publicTime/deposit",method = RequestMethod.POST)
    public TimeDepositResult timeDeposit(TimeDepositEntity timeDepositEntity) {
        return publicTime.timeDeposit(timeDepositEntity);
    }

    @Transactional
    @Override
    @RequiresPermissions("public:time:withdraw")
    @RequestMapping(value = "/api/publicTime/withdraw",method = RequestMethod.POST)
    public TimeWithdrawBo timeWithdraw(TimeWithdrawEntity timeWithdrawEntity) {
        return publicTime.timeWithdraw(timeWithdrawEntity);
    }

    @Transactional
    @Override
    @RequiresPermissions("public:time:withdraw")
    @RequestMapping(value = "/api/publicTime/deposit",method = RequestMethod.GET)
    public List<TimeDepositItemBo> getTimeDeposit(String accountID) {
        return publicTime.getTimeDeposit(accountID);
    }

    @Transactional
    @Override
    @RequiresPermissions("public:time:withdraw")
    @RequestMapping(value = "/api/publicTime/demand",method = RequestMethod.POST)
    public TimeToDemandBo timeToDemand(TimeToDemandEntity timeToDemandEntity) {
        return publicTime.timeToDemand(timeToDemandEntity);
    }
}
