package com.imbus.bank.timeDepositModule.service.impl;

import com.imbus.bank.timeDepositModule.bo.DepositBo;
import com.imbus.bank.timeDepositModule.dao.DepositDao;
import com.imbus.bank.timeDepositModule.service.IDeposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2020-9-7.
 */
@Service
public class DepositImpl implements IDeposit {
    @Autowired
    private DepositDao depositDao;

    @Override
    public List<DepositBo> getAccountDeposit(String accountID) {
        return depositDao.getAccountDeposit(accountID);
    }
}
