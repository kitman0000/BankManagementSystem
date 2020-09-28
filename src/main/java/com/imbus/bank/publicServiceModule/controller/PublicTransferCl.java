package com.imbus.bank.publicServiceModule.controller;

import com.imbus.bank.componet.Entity.TransferEntity;
import com.imbus.bank.componet.ITransferService;
import com.imbus.bank.componet.type.TransferResult;
import com.imbus.bank.publicServiceModule.service.IPublicTransfer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2020-8-25.
 */
@Api(description = "对公转账业务接口")
@RestController
public class PublicTransferCl implements ITransferService{

    @Autowired
    private IPublicTransfer publicTransfer;

    @Override
    @Transactional
    @RequestMapping(value = "/api/publicTransfer/transfer",method = RequestMethod.POST)
    public TransferResult transfer(TransferEntity transferEntity) {
        return publicTransfer.transfer(transferEntity);
    }
}
