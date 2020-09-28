package com.imbus.bank.clientSearchModule.service.impl;

import com.imbus.bank.clientSearchModule.bo.PublicClientBo;
import com.imbus.bank.clientSearchModule.bo.PublicClientDetailBo;
import com.imbus.bank.clientSearchModule.dao.PublicClientSearchDao;
import com.imbus.bank.clientSearchModule.entity.ClientSearchEntity;
import com.imbus.bank.clientSearchModule.service.IPublicClient;
import com.imbus.bank.publicServiceModule.bo.PublicAccountBo;
import com.imbus.bank.publicServiceModule.dao.PublicAccountDao;
import com.imbus.bank.utils.PageDivideUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2020-8-9.
 */
@Service
public class PublicClientSearchImpl implements IPublicClient {

    final private int ROWS_ONE_PAGE = 10;

    @Autowired
    private PublicClientSearchDao publicClientSearchDao;

    @Autowired
    private PublicAccountDao publicAccountDao;

    @Override
    public int getPublicClientPage(ClientSearchEntity clientSearchEntity) {
        int count = publicClientSearchDao.getPublicClientPage(clientSearchEntity);
        return PageDivideUtil.getCountOfPages(count,ROWS_ONE_PAGE);
    }

    @Override
    public List<PublicClientBo> getPublicList(ClientSearchEntity clientSearchEntity, int page) {
        int startRow = (page -1) * ROWS_ONE_PAGE;
        return publicClientSearchDao.getPublicList(clientSearchEntity,startRow,ROWS_ONE_PAGE);
    }

    @Override
    public PublicClientDetailBo getPublicDetail(int id) {
        return publicClientSearchDao.getPublicDetail(id);
    }

    @Override
    public List<PublicAccountBo> getPublicAccount(int clientID) {
        return publicAccountDao.getClientAccount(clientID);
    }


}
