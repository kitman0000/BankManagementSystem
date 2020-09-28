package com.imbus.bank.timeDepositModule.controller;

import com.imbus.bank.timeDepositModule.bo.DepositBo;
import com.imbus.bank.timeDepositModule.service.IDeposit;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-9-7.
 */
@RestController
public class DepositCl {
    @Autowired
    private IDeposit deposit;

    @ApiOperation(value = "本接口暂时不要使用")
    @RequestMapping(value = "/api/deposit/accountDeposit",method = RequestMethod.GET)
    public List<DepositBo> getAccountDeposit(String accountID){
        return deposit.getAccountDeposit(accountID);
    }
}
