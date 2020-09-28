package com.imbus.bank.publicServiceModule.service;

import com.imbus.bank.componet.type.AddClientResult;
import com.imbus.bank.publicServiceModule.entity.PublicClientEntity;

/**
 * Created by zhong on 2020-8-11.
 */
public interface IPublicClient {
    AddClientResult addClient(PublicClientEntity clientEntity);
}
