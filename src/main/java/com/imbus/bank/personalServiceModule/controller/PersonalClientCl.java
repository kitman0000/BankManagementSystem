package com.imbus.bank.personalServiceModule.controller;

import com.imbus.bank.componet.Entity.ClientEntity;
import com.imbus.bank.componet.IClientService;
import com.imbus.bank.componet.type.AddClientResult;
import com.imbus.bank.personalServiceModule.entity.PersonalClientEntity;
import com.imbus.bank.personalServiceModule.service.IPersonalClient;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "新增个人客户接口")
@RestController
public class PersonalClientCl {

    @Autowired
    IPersonalClient personalClient;

    @RequiresPermissions("personal:client:add")
    @RequestMapping(value = "/api/personalService/client",method = RequestMethod.POST)
    public AddClientResult addClient(PersonalClientEntity clientEntity) {
        return personalClient.addClient(clientEntity);
    }
}
