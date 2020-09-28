package com.imbus.bank.publicServiceModule.service.impl;

import com.imbus.bank.common.AgencyCommon;
import com.imbus.bank.common.BankAccountCommon;
import com.imbus.bank.componet.Entity.CancelAccountEntity;
import com.imbus.bank.componet.type.CancelAccountResult;
import com.imbus.bank.componet.type.CreateAccountResult;
import com.imbus.bank.publicServiceModule.dao.PublicAccountDao;
import com.imbus.bank.publicServiceModule.entity.PublicAccountEntity;
import com.imbus.bank.publicServiceModule.service.IPublicAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhong on 2020-8-11.
 */
@Service
public class PublicAccountImpl implements IPublicAccount{
    @Autowired
    private AgencyCommon agencyCommon;

    @Autowired
    private PublicAccountDao publicAccountDao;

    @Override
    public CreateAccountResult createAccount(PublicAccountEntity accountEntity) {
        int agencyID = agencyCommon.getUserAgency();
        accountEntity.setAgencyID(agencyID);

        // 加密密码
        String pwd = BankAccountCommon.encodePwd(accountEntity.getPwd());
        accountEntity.setPwd(pwd);

        String accountType = accountEntity.getAccountType(); // 用户要申请的账户类型
        int basicAccountNumber = publicAccountDao.getBasicAccountNumber(accountEntity.getClientID());

        // 如果申请基本账户，但已存在基本账户，不允许申请
        if(accountType.equals("1") && basicAccountNumber == 1){
            return CreateAccountResult.CREATE_ACCOUNT_FAILED;
        }

        // 如果申请非基本账户，但不存在基本账户，不允许申请
        if(!accountType.equals("1") && basicAccountNumber == 0){
            return CreateAccountResult.CREATE_ACCOUNT_FAILED;
        }

        publicAccountDao.createAccountApply(accountEntity);
        return CreateAccountResult.CREATE_ACCOUNT_SUCCESS;
    }

    @Override
    public CancelAccountResult cancelAccount(CancelAccountEntity cancelAccountEntity) {
        String accountID = cancelAccountEntity.getAccountId();
        String pwd = cancelAccountEntity.getPwd();

        pwd = BankAccountCommon.encodePwd(pwd);

        if(publicAccountDao.checkAccountPwd(accountID,pwd) != 1){
            return CancelAccountResult.CANCEL_ACCOUNT_FAILED;
        }
        publicAccountDao.cancelAccount(cancelAccountEntity.getAccountId());
        return CancelAccountResult.CANCEL_ACCOUNT_SUCCESS;
    }
}
