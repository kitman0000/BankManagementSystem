package com.imbus.bank.publicServiceModule.controller;

import com.imbus.bank.componet.Entity.ClientEntity;
import com.imbus.bank.componet.IClientService;
import com.imbus.bank.componet.type.AddClientResult;
import com.imbus.bank.publicServiceModule.entity.PublicClientEntity;
import com.imbus.bank.publicServiceModule.service.IPublicClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2020-8-11.
 */
@Api(description = "新增客户接口")
@RestController
public class PublicClientCl{

    @Autowired
    private IPublicClient publicClient;

    @RequestMapping(value = "/api/publicClient/client",method = RequestMethod.POST)
    public AddClientResult addClient(PublicClientEntity clientEntity) {
        return publicClient.addClient(clientEntity);
    }
}
