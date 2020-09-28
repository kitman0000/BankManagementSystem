package com.imbus.bank.nettingModule.service.impl;

import com.imbus.bank.nettingModule.bo.NettingBo;
import com.imbus.bank.nettingModule.bo.NettingWarningBo;
import com.imbus.bank.nettingModule.dao.NettingDao;
import com.imbus.bank.nettingModule.service.INetting;
import com.imbus.bank.utils.PageDivideUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2020-9-28.
 */
@Service
public class NettingImpl implements INetting {

    @Autowired
    private NettingDao nettingDao;

    final private int ROWS_ONE_PAGE = 10;

    @Override
    public int getNettingPage() {
        int amount = nettingDao.getNettingAmount();
        return PageDivideUtil.getCountOfPages(amount,ROWS_ONE_PAGE);
    }

    @Override
    public List<NettingBo> getNettingList(int page) {
        int startRow = (page -1) * ROWS_ONE_PAGE;
        return nettingDao.getNettingList(startRow,ROWS_ONE_PAGE);
    }

    @Override
    public List<NettingWarningBo> getNettingWarning(String nettingSign) {
        return nettingDao.getNettingWarning(nettingSign);
    }
}
