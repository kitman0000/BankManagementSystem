package com.imbus.bank.clientSearchModule.service;

import com.imbus.bank.clientSearchModule.bo.PrivateClientBo;
import com.imbus.bank.clientSearchModule.bo.PrivateClientDetailBo;
import com.imbus.bank.clientSearchModule.entity.ClientSearchEntity;
import com.imbus.bank.personalServiceModule.bo.PersonalAccountBo;
import com.imbus.bank.timeDepositModule.bo.DepositBo;

import java.util.List;

/**
 * Created by zhong on 2020-8-9.
 */
public interface IPrivateClient {
    int getPrivateClientPage(ClientSearchEntity clientSearchEntity);

    List<PrivateClientBo> getPrivateList(ClientSearchEntity clientSearchEntity, int page);

    PrivateClientDetailBo getPrivateDetail(int id);

    List<PersonalAccountBo> getPersonalAccount(int clientID);

}
