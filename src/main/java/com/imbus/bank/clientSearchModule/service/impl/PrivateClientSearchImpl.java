package com.imbus.bank.clientSearchModule.service.impl;

import com.imbus.bank.clientSearchModule.bo.PrivateClientBo;
import com.imbus.bank.clientSearchModule.bo.PrivateClientDetailBo;
import com.imbus.bank.clientSearchModule.dao.PrivateClientSearchDao;
import com.imbus.bank.clientSearchModule.entity.ClientSearchEntity;
import com.imbus.bank.clientSearchModule.service.IPrivateClient;
import com.imbus.bank.personalServiceModule.bo.PersonalAccountBo;
import com.imbus.bank.personalServiceModule.dao.PersonalAccountDao;
import com.imbus.bank.utils.PageDivideUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2020-8-9.
 */
@Service
public class PrivateClientSearchImpl implements IPrivateClient {

    @Autowired
    private PrivateClientSearchDao privateClientSearchDao;

    @Autowired
    private PersonalAccountDao personalAccountDao;

    final private int ROWS_ONE_PAGE = 10;

    @Override
    public int getPrivateClientPage(ClientSearchEntity clientSearchEntity) {
        int count = privateClientSearchDao.getPrivateClientPage(clientSearchEntity);
        return PageDivideUtil.getCountOfPages(count,ROWS_ONE_PAGE);
    }

    @Override
    public List<PrivateClientBo> getPrivateList(ClientSearchEntity clientSearchEntity, int page) {
        int startRow = (page -1) * ROWS_ONE_PAGE;
        return privateClientSearchDao.getPrivateList(clientSearchEntity,startRow,ROWS_ONE_PAGE);
    }

    @Override
    public PrivateClientDetailBo getPrivateDetail(int id) {
        return privateClientSearchDao.getPrivateDetail(id);
    }

    @Override
    public List<PersonalAccountBo> getPersonalAccount(int clientID) {
        return personalAccountDao.getClientAccount(clientID);
    }
}
