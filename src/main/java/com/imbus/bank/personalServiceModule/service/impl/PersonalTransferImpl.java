package com.imbus.bank.personalServiceModule.service.impl;

import com.imbus.bank.common.BankAccountCommon;
import com.imbus.bank.common.BookCommon;
import com.imbus.bank.componet.Entity.TransferEntity;
import com.imbus.bank.componet.type.TransferResult;
import com.imbus.bank.personalServiceModule.dao.PersonalAccountDao;
import com.imbus.bank.personalServiceModule.service.IPersonalTransfer;
import com.imbus.bank.publicServiceModule.dao.PublicAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-11-12.
 */
@Service
public class PersonalTransferImpl implements IPersonalTransfer {
    @Autowired
    private BankAccountCommon bankAccountCommon;

    @Autowired
    private PublicAccountDao publicAccountDao;

    @Autowired
    private PersonalAccountDao personalAccountDao;

    @Autowired
    private BookCommon bookCommon;

    @Override
    public TransferResult transfer(TransferEntity transferEntity) {

        String accountID = transferEntity.getAccount();
        String pwd = BankAccountCommon.encodePwd(transferEntity.getPwd());
        BigDecimal amount = transferEntity.getAmount();

        String targetAccountID = transferEntity.getTargetAccount();

        // 检查密码
        if(personalAccountDao.checkAccountPwd(accountID,pwd) != 1){
            return TransferResult.TRANSFER_FAILED;
        }

        // 是否有足够余额
        if(personalAccountDao.getAccountBalance(accountID).compareTo(amount) == -1){
            return TransferResult.NO_ENOUGH_BALANCE;
        }

        switch (bankAccountCommon.getAccountType(targetAccountID)){
            // 对公账户
            case PUBLIC:
                // 是否存在该账户
                if(publicAccountDao.getAccountBalance(targetAccountID) == null){
                    return TransferResult.TARGET_NOT_EXIST;
                }
                // 转出方扣除余额
                personalAccountDao.removeAccountBalance(accountID,amount);
                // 资产端：减少
                bookCommon.addFundBill(amount.negate(),accountID,"转出("+targetAccountID+")",1);
                // 负债端：减少
                bookCommon.addFundBill(amount.negate(),accountID,"转出("+targetAccountID+")",2);

                // 转入方增加余额
                publicAccountDao.addAccountBalance(targetAccountID,amount);
                // 资产端：增加
                bookCommon.addFundBill(amount,targetAccountID,"转入("+accountID+")",1);
                // 负债端：增加
                bookCommon.addFundBill(amount,targetAccountID,"转入("+accountID+")",2);
                break;

            // 个人账户
            case PRIVATE:
                // 是否存在该账户
                if(personalAccountDao.getAccountBalance(targetAccountID) == null){
                    return TransferResult.TARGET_NOT_EXIST;
                }
                // 转出方扣除余额
                personalAccountDao.removeAccountBalance(accountID,amount);
                // 资产端：减少
                bookCommon.addFundBill(amount.negate(),accountID,"转出("+targetAccountID+")",1);
                // 负债端：减少
                bookCommon.addFundBill(amount.negate(),accountID,"转出("+targetAccountID+")",2);

                // 转入方增加余额
                personalAccountDao.addAccountBalance(targetAccountID,amount);
                // 资产端：增加
                bookCommon.addFundBill(amount,targetAccountID,"转入("+accountID+")",1);
                // 负债端：增加
                bookCommon.addFundBill(amount,targetAccountID,"转入("+accountID+")",2);

                break;
            // 跨行转账
            case OTHER_BANK:
                // 转出方扣除余额
                personalAccountDao.removeAccountBalance(accountID,amount);
                // 资产端：减少
                bookCommon.addFundBill(amount.negate(),accountID,"转出("+targetAccountID+")",1);
                // 负债端：减少
                bookCommon.addFundBill(amount.negate(),accountID,"转出("+targetAccountID+")",2);
                break;
        }
        return TransferResult.TRANSFER_SUCCESS;
    }
}
