package com.imbus.bank.clientSearchModule.controller;

import com.imbus.bank.clientSearchModule.bo.PrivateClientBo;
import com.imbus.bank.clientSearchModule.bo.PublicClientBo;
import com.imbus.bank.clientSearchModule.bo.PublicClientDetailBo;
import com.imbus.bank.clientSearchModule.entity.ClientSearchEntity;
import com.imbus.bank.clientSearchModule.service.IPrivateClient;
import com.imbus.bank.clientSearchModule.service.IPublicClient;
import com.imbus.bank.personalServiceModule.bo.PersonalAccountBo;
import com.imbus.bank.publicServiceModule.bo.PublicAccountBo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-8-9.
 */
@Api(value = "对公用户查询接口")
@RestController
public class PublicClientSearchCl {

    @Autowired
    private IPublicClient publicClient;

    @RequestMapping(value = "/api/clientSearch/publicPage",method = RequestMethod.GET)
    public int getPublicClientPage(ClientSearchEntity clientSearchEntity){
        return publicClient.getPublicClientPage(clientSearchEntity);
    }

    @RequestMapping(value = "/api/clientSearch/publicList",method = RequestMethod.GET)
    public List<PublicClientBo> getPublicList(ClientSearchEntity clientSearchEntity, int page){
        return publicClient.getPublicList(clientSearchEntity,page);
    }

    @RequestMapping(value = "/api/clientSearch/publicDetail",method = RequestMethod.GET)
    public PublicClientDetailBo getPublicDetail(int id){
        return publicClient.getPublicDetail(id);
    }

    @RequestMapping(value = "/api/clientSearch/publicAccount",method = RequestMethod.GET)
    public List<PublicAccountBo> getPublicAccount(int clientID){
        return publicClient.getPublicAccount(clientID);
    }
}
