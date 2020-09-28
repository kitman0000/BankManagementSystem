package com.imbus.bank.personalServiceModule.service.impl;


import com.imbus.bank.common.DateUtil;
import com.imbus.bank.componet.type.AddClientResult;
import com.imbus.bank.personalServiceModule.entity.PersonalClientEntity;
import com.imbus.bank.personalServiceModule.service.IPersonalClient;
import com.imbus.bank.personalServiceModule.dao.PersonalClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class PersonalClientImpl implements IPersonalClient {
    @Autowired
    private PersonalClientDao personalClientDao;

    @Override
    public AddClientResult addClient(PersonalClientEntity personalClientEntity) {
        personalClientEntity.setAuthDate(DateUtil.getDatestamp());
        personalClientDao.addClient(personalClientEntity);
        return AddClientResult.ADD_CLIENT_SUCCESS;
    }
}
