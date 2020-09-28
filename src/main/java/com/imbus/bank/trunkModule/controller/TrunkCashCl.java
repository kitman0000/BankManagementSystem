package com.imbus.bank.trunkModule.controller;

import com.imbus.bank.trunkModule.service.ITrunkCash;
import com.imbus.bank.trunkModule.type.UpdateTrunkCashResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-9-12.
 */
@RestController
@Api(description = "尾箱现金管理接口")
public class TrunkCashCl {
    @Autowired
    private ITrunkCash trunkCash;

    @Transactional
    @RequestMapping(value = "/api/trunkCash/addCash",method = RequestMethod.POST)
    public UpdateTrunkCashResult addTrunkCash(int id, BigDecimal amount) {
        return trunkCash.addTrunkCash(id,amount);
    }

    @Transactional
    @RequestMapping(value = "/api/trunkCash/removeCash",method = RequestMethod.POST)
    public UpdateTrunkCashResult removeTrunkCash(int id, BigDecimal amount) {
        return trunkCash.removeTrunkCash(id,amount);
    }
}
