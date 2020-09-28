package com.imbus.bank.clientSearchModule.service;

import com.imbus.bank.clientSearchModule.bo.PublicClientBo;
import com.imbus.bank.clientSearchModule.bo.PublicClientDetailBo;
import com.imbus.bank.clientSearchModule.entity.ClientSearchEntity;
import com.imbus.bank.publicServiceModule.bo.PublicAccountBo;

import java.util.List;

/**
 * Created by zhong on 2020-8-9.
 */
public interface IPublicClient {
    int getPublicClientPage(ClientSearchEntity clientSearchEntity);

    List<PublicClientBo> getPublicList(ClientSearchEntity clientSearchEntity, int page);

    PublicClientDetailBo getPublicDetail(int id);

    List<PublicAccountBo> getPublicAccount(int clientID);

}
