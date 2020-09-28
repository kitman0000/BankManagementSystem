package com.imbus.bank.publicServiceModule.service.impl;

import com.imbus.bank.componet.type.AddClientResult;
import com.imbus.bank.publicServiceModule.dao.PublicClientDao;
import com.imbus.bank.publicServiceModule.entity.PublicClientEntity;
import com.imbus.bank.publicServiceModule.service.IPublicClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zhong on 2020-8-11.
 */
@Service
public class PublicClientImpl implements IPublicClient {

    @Autowired
    private PublicClientDao publicClientDao;

    @Override
    public AddClientResult addClient(PublicClientEntity clientEntity) {
        clientEntity.setAuthDate(new Date());
        publicClientDao.addClient(clientEntity);
        return AddClientResult.ADD_CLIENT_SUCCESS;
    }
}
