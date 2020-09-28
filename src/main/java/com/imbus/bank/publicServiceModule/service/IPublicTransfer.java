package com.imbus.bank.publicServiceModule.service;

import com.imbus.bank.componet.Entity.TransferEntity;
import com.imbus.bank.componet.type.TransferResult;

/**
 * Created by zhong on 2020-8-25.
 */
public interface IPublicTransfer {
    TransferResult transfer(TransferEntity transferEntity);

}
