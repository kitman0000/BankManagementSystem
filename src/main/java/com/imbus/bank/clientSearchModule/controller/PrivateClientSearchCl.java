package com.imbus.bank.clientSearchModule.controller;

import com.imbus.bank.clientSearchModule.bo.PrivateClientBo;
import com.imbus.bank.clientSearchModule.bo.PrivateClientDetailBo;
import com.imbus.bank.clientSearchModule.entity.ClientSearchEntity;
import com.imbus.bank.clientSearchModule.service.IPrivateClient;
import com.imbus.bank.personalServiceModule.bo.PersonalAccountBo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-8-9.
 */
@Api(value = "个人用户查询接口")
@RestController
public class PrivateClientSearchCl {

    @Autowired
    IPrivateClient privateClient;

    @RequiresPermissions("personal:client:search")
    @RequestMapping(value = "/api/clientSearch/personalPage",method = RequestMethod.GET)
    public int getPrivateClientPage(ClientSearchEntity clientSearchEntity){
        return privateClient.getPrivateClientPage(clientSearchEntity);
    }

    @RequiresPermissions("personal:client:search")
    @RequestMapping(value = "/api/clientSearch/personalList",method = RequestMethod.GET)
    public List<PrivateClientBo> getPrivateList(ClientSearchEntity clientSearchEntity, int page){
        return privateClient.getPrivateList(clientSearchEntity,page);
    }

    @RequiresPermissions("personal:client:search")
    @RequestMapping(value = "/api/clientSearch/personalDetail",method = RequestMethod.GET)
    public PrivateClientDetailBo getPrivateDetail(int id){
        return privateClient.getPrivateDetail(id);
    }

    @RequiresPermissions("personal:client:search")
    @RequestMapping(value = "/api/clientSearch/personalAccount",method = RequestMethod.GET)
    public List<PersonalAccountBo> getPersonalAccount(int clientID){
        return privateClient.getPersonalAccount(clientID);
    }
}
