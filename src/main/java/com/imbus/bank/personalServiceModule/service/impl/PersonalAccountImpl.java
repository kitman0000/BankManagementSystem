package com.imbus.bank.personalServiceModule.service.impl;


import com.imbus.bank.common.AgencyCommon;
import com.imbus.bank.common.BankAccountCommon;
import com.imbus.bank.common.DateUtil;
import com.imbus.bank.common.UserCommon;
import com.imbus.bank.componet.Entity.CancelAccountEntity;
import com.imbus.bank.componet.type.CancelAccountResult;
import com.imbus.bank.componet.type.CreateAccountResult;
import com.imbus.bank.personalServiceModule.dao.PersonalAccountDao;
import com.imbus.bank.personalServiceModule.entity.PersonalAccountEntity;
import com.imbus.bank.personalServiceModule.service.IPersonalAccount;
import com.imbus.bank.trunkModule.dao.TrunkCardDao;
import com.imbus.bank.trunkModule.dao.TrunkCashDao;
import com.imbus.bank.trunkModule.service.impl.TrunkCashImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PersonalAccountImpl implements IPersonalAccount {
    @Autowired
    private AgencyCommon agencyCommon;

    @Autowired
    private BankAccountCommon bankAccountCommon;

    @Autowired
    private PersonalAccountDao personalAccountDao;

    @Autowired
    private TrunkCardDao trunkCardDao;

    @Autowired
    private TrunkCashImpl trunkCash;

    @Override
    public CreateAccountResult createAccount(PersonalAccountEntity accountEntity) {

        // 开户核准号暂定为yyyyMMddHHmmssSSS+id

        String authNo = "";
        authNo = DateUtil.formatLongyyyyMMddHHmmssSSSFormatByDate();

        accountEntity.setAuthDate(DateUtil.getDatestamp());

        String bankAccount = "";

        int trunkID;
        trunkID = trunkCash.getUserTrunk();
        switch (accountEntity.getAccountType()){
            case "1":bankAccount = trunkCardDao.selectTrunkFirstClassCard(trunkID);break;
            case "2":bankAccount = trunkCardDao.selectTrunkSecondClassCard(trunkID);break;
        }

        accountEntity.setId(bankAccount);

        int agencyID = agencyCommon.getUserAgency();
        accountEntity.setAgencyID(agencyID);

        // 加密密码
        String pwd = BankAccountCommon.encodePwd(accountEntity.getPwd());
        accountEntity.setPwd(pwd);

        String accountType = accountEntity.getAccountType(); // 账户类型
        int classOneCardNumber = personalAccountDao.getClassOneCardNumber(accountEntity.getClientID());
        int classTwoCardNumber = personalAccountDao.getClassTwoCardNumber(accountEntity.getClientID());

        // 每个客户只允许拥有有且只有一个一类卡
        if(accountType.equals("1") && classOneCardNumber == 1){
            return CreateAccountResult.CREATE_ACCOUNT_FAILED;
        }

        //客户必须在拥有一类卡的前提下，才能创建二类卡
        if(!accountType.equals("1") && classOneCardNumber == 0){
            return CreateAccountResult.CREATE_ACCOUNT_FAILED;
        }

        //二类卡至多有4张
        if(!accountType.equals("1") && classTwoCardNumber >= 4){
            return CreateAccountResult.CREATE_ACCOUNT_FAILED;
        }

        personalAccountDao.createAccount(accountEntity);
        return CreateAccountResult.CREATE_ACCOUNT_SUCCESS;
    }

    @Override
    public CancelAccountResult cancelAccount(CancelAccountEntity cancelAccountEntity) {
        String accountID = cancelAccountEntity.getAccountId();
        String pwd = cancelAccountEntity.getPwd();

        pwd = BankAccountCommon.encodePwd(pwd);

        if(personalAccountDao.checkAccountPwd(accountID,pwd) != 1){
            return CancelAccountResult.CANCEL_ACCOUNT_FAILED;
        }
        personalAccountDao.cancelAccount(cancelAccountEntity.getAccountId());
        return CancelAccountResult.CANCEL_ACCOUNT_SUCCESS;
    }
}
