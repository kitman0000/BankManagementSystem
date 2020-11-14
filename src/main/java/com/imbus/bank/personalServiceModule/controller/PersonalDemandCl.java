package com.imbus.bank.personalServiceModule.controller;

import com.imbus.bank.componet.Entity.DemandDepositEntity;
import com.imbus.bank.componet.Entity.DemandToTimeEntity;
import com.imbus.bank.componet.Entity.DemandWithdrawEntity;
import com.imbus.bank.componet.IDemandService;
import com.imbus.bank.componet.type.DemandDepositResult;
import com.imbus.bank.componet.type.DemandToTimeResult;
import com.imbus.bank.componet.type.DemandWithdrawResult;
import com.imbus.bank.personalServiceModule.service.IPersonalDemand;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "个人活期接口")
@RestController
public class PersonalDemandCl implements IDemandService {
    @Autowired
    private IPersonalDemand personalDemand;

    @Override
    @Transactional
    @RequiresPermissions("personal:demand:deposit")
    @RequestMapping(value = "/api/personalDemand/deposit",method = RequestMethod.POST)
    public DemandDepositResult demandDeposit(DemandDepositEntity demandDepositEntity) {
        return personalDemand.demandDeposit(demandDepositEntity);
    }

    @Override
    @Transactional
    @RequiresPermissions(value = "personal:demand:withdraw")
    @RequestMapping(value = "/api/personalDemand/withDraw",method = RequestMethod.POST)
    public DemandWithdrawResult demandWithdraw(DemandWithdrawEntity demandWithdrawEntity) {
        return personalDemand.demandWithdraw(demandWithdrawEntity);
    }

    @Override
    @Transactional
    @RequiresPermissions(value = "personal:demand:toTime")
    @RequestMapping(value = "/api/personalDemand/time",method = RequestMethod.POST)
    public DemandToTimeResult demandToTime(DemandToTimeEntity demandToTimeEntity) {
        return personalDemand.demandToTime(demandToTimeEntity);
    }
}
