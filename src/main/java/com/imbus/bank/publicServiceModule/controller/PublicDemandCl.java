package com.imbus.bank.publicServiceModule.controller;

import com.imbus.bank.componet.Entity.DemandDepositEntity;
import com.imbus.bank.componet.Entity.DemandToTimeEntity;
import com.imbus.bank.componet.Entity.DemandWithdrawEntity;
import com.imbus.bank.componet.IDemandService;
import com.imbus.bank.componet.type.DemandDepositResult;
import com.imbus.bank.componet.type.DemandToTimeResult;
import com.imbus.bank.componet.type.DemandWithdrawResult;
import com.imbus.bank.publicServiceModule.service.IPublicDemand;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2020-8-19.
 * 对公活期业务Controller，实现IDemandService接口
 */
@Api(description = "对公活期接口")
@RestController
public class PublicDemandCl implements IDemandService{

    @Autowired
    private IPublicDemand publicDemand;

    @Override
    @Transactional
    @RequiresPermissions("public:demand:deposit")
    @RequestMapping(value = "/api/publicDemand/deposit",method = RequestMethod.POST)
    public DemandDepositResult demandDeposit(DemandDepositEntity demandDepositEntity) {
        return publicDemand.demandDeposit(demandDepositEntity);
    }

    @Override
    @Transactional
    @RequiresPermissions("public:demand:withdraw")
    @RequestMapping(value = "/api/publicDemand/withDraw",method = RequestMethod.POST)
    public DemandWithdrawResult demandWithdraw(DemandWithdrawEntity demandWithdrawEntity) {
        return publicDemand.demandWithdraw(demandWithdrawEntity);
    }

    @Override
    @Transactional
    @RequiresPermissions("public:demand:toTime")
    @RequestMapping(value = "/api/publicDemand/time",method = RequestMethod.POST)
    public DemandToTimeResult demandToTime(DemandToTimeEntity demandToTimeEntity) {
        return publicDemand.demandToTime(demandToTimeEntity);
    }
}
