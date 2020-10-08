package com.imbus.bank.cashManageModule.controller;

import com.imbus.bank.cashManageModule.bo.AgencyCashBo;
import com.imbus.bank.cashManageModule.service.ICashManage;
import com.imbus.bank.cashManageModule.type.CashToFundResult;
import com.imbus.bank.cashManageModule.type.FundToCashResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-9-14.
 */
@RestController
public class CashManageCl{
    @Autowired
    private ICashManage cashManage;

    @RequestMapping(value = "/api/cashManage/cashToFund",method = RequestMethod.POST)
    public CashToFundResult cashToFund(int agencyID, BigDecimal amount) {
        return cashManage.cashToFund(agencyID,amount);
    }

    @RequestMapping(value = "/api/cashManage/fundToCash",method = RequestMethod.POST)
    public FundToCashResult fundToCash(int agencyID, BigDecimal amount) {
        return cashManage.fundToCash(agencyID,amount);
    }

    @RequestMapping(value = "/api/cashManage/cash",method = RequestMethod.GET)
    public List<AgencyCashBo> getAgencyCash(){
        return cashManage.getAgencyCash();
    }
}
