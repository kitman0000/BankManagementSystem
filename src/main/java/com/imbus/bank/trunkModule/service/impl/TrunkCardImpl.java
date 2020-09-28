package com.imbus.bank.trunkModule.service.impl;

import com.imbus.bank.common.BankAccountCommon;
import com.imbus.bank.common.DateUtil;
import com.imbus.bank.trunkModule.bo.TrunkCardBo;
import com.imbus.bank.trunkModule.dao.TrunkCardDao;
import com.imbus.bank.trunkModule.service.ITrunkCard;
import com.imbus.bank.trunkModule.type.TrunkCardResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2020-9-13.
 */
@Service
public class TrunkCardImpl implements ITrunkCard{

    @Autowired
    private TrunkCardDao trunkCardDao;

    @Autowired
    private TrunkCashImpl trunkCash;

    @Autowired
    private BankAccountCommon bankAccountCommon;

    /***
     * 向尾箱添加银行卡，一次添加25张一类卡，25张二类卡
     * @param trunkID
     * @return
     */
    @Override
    public TrunkCardResult addTrunkCard(int trunkID) {
        // 一类卡
        for (int i = 0;i < 25;i++){
            String binCode = BankAccountCommon.BIN_CODE_PRIVATE_1;
            String bankAccount = bankAccountCommon.getNewBankAccountNumber(binCode);
            trunkCardDao.addTrunkCard(trunkID,bankAccount);
        }

        // 二类卡
        for (int i = 0;i < 25;i++){
            String binCode = BankAccountCommon.BIN_CODE_PRIVATE_2;
            String bankAccount = bankAccountCommon.getNewBankAccountNumber(binCode);
            trunkCardDao.addTrunkCard(trunkID,bankAccount);
        }

        return TrunkCardResult.SUCCESS;
    }

    /**
     * 移除尾箱内的银行卡，供外部调用，尾箱为用户自身的尾箱
     * @param accountID 银行卡号
     * @return
     */
    public TrunkCardResult removeTrunkCard(String accountID) {
        int trunkID = trunkCash.getUserTrunk();

        // 如果用户没有尾箱
        if(trunkID == 0){
            return TrunkCardResult.FAILED;
        }

        removeTrunkCard(trunkID,accountID);
        return TrunkCardResult.SUCCESS;
    }

        @Override
    public TrunkCardResult removeTrunkCard(int trunkID, String accountID) {
        trunkCardDao.removeTrunkCard(trunkID,accountID);
        return TrunkCardResult.SUCCESS;
    }

    @Override
    public List<TrunkCardBo> getUserTrunkCard(int userID) {
        int trunkID = trunkCash.getUserTrunk();
        return trunkCardDao.getUserTrunkCard(trunkID);
    }
}
