package com.imbus.bank.personalServiceModule.service;

import com.imbus.bank.componet.type.AddClientResult;
import com.imbus.bank.personalServiceModule.entity.PersonalClientEntity;

public interface IPersonalClient {
    AddClientResult addClient(PersonalClientEntity personalClientEntity);
}
