package com.imbus.bank.personalServiceModule.controller;

import com.imbus.bank.componet.Entity.TransferEntity;
import com.imbus.bank.componet.ITransferService;
import com.imbus.bank.componet.type.TransferResult;
import com.imbus.bank.personalServiceModule.service.IPersonalTransfer;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2020-11-12.
 */
@RestController
public class PersonalTransferCl implements ITransferService {

    @Autowired
    private IPersonalTransfer personalTransfer;

    @Override
    @Transactional
    @RequiresPermissions("personal:transfer")
    @RequestMapping(value = "/api/personalTransfer/transfer",method = RequestMethod.POST)
    public TransferResult transfer(TransferEntity transferEntity) {
        return personalTransfer.transfer(transferEntity);
    }
}
