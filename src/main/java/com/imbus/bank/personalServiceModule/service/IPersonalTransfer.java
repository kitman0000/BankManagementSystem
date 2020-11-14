package com.imbus.bank.personalServiceModule.service;

import com.imbus.bank.componet.Entity.TransferEntity;
import com.imbus.bank.componet.type.TransferResult;

/**
 * Created by zhong on 2020-11-12.
 */
public interface IPersonalTransfer {
    TransferResult transfer(TransferEntity transferEntity);

}
