package com.imbus.bank.publicServiceModule.service.impl;

import com.imbus.bank.agencyModule.dao.AgencyDao;
import com.imbus.bank.common.AgencyCommon;
import com.imbus.bank.common.BankAccountCommon;
import com.imbus.bank.common.BookCommon;
import com.imbus.bank.componet.Entity.DemandDepositEntity;
import com.imbus.bank.componet.Entity.DemandToTimeEntity;
import com.imbus.bank.componet.Entity.DemandWithdrawEntity;
import com.imbus.bank.componet.type.DemandDepositResult;
import com.imbus.bank.componet.type.DemandToTimeResult;
import com.imbus.bank.componet.type.DemandWithdrawResult;
import com.imbus.bank.publicServiceModule.dao.PublicAccountDao;
import com.imbus.bank.publicServiceModule.dao.PublicRateDao;
import com.imbus.bank.publicServiceModule.dao.PublicTimeDepositDao;
import com.imbus.bank.publicServiceModule.service.IPublicDemand;
import com.imbus.bank.trunkModule.dao.TrunkCashDao;
import com.imbus.bank.trunkModule.dao.TrunkDao;
import com.imbus.bank.trunkModule.service.impl.TrunkCashImpl;
import com.imbus.bank.trunkModule.service.impl.TrunkImpl;
import com.imbus.bank.trunkModule.type.UpdateTrunkCashResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhong on 2020-8-19.
 */
@Service
public class PublicDemandImpl implements IPublicDemand {
    @Autowired
    private AgencyDao agencyDao;

    @Autowired
    private AgencyCommon agencyCommon;

    @Autowired
    private PublicAccountDao publicAccountDao;

    @Autowired
    private PublicTimeDepositDao publicTimeDepositDao;

    @Autowired
    private PublicRateDao publicRateDao;

    @Autowired
    private BookCommon bookCommon;

    @Autowired
    private TrunkCashImpl trunkCash;

    @Override
    public DemandDepositResult demandDeposit(DemandDepositEntity demandDepositEntity) {
        String accountID = demandDepositEntity.getAccountID();
        String pwd = BankAccountCommon.encodePwd(demandDepositEntity.getPwd());
        BigDecimal amount = demandDepositEntity.getAmount();

        // 检查账号和密码
        if(publicAccountDao.checkAccountPwd(accountID,pwd) != 1){
            return DemandDepositResult.DEMAND_DEPOSIT_FAILED;
        }

        // 添加现金
        //int agencyID = agencyCommon.getUserAgency();
        trunkCash.addTellerTrunkCash(amount);

        // 添加余额
        publicAccountDao.addAccountBalance(accountID,amount);

        // 现金账目：增加
        bookCommon.addCashBill(amount,accountID,"活期存款",true,2);

        // 负债账目：增加
        bookCommon.addFundBill(amount,accountID,"活期存款",2);

        return DemandDepositResult.DEMAND_DEPOSIT_SUCCESS;
    }

    @Override
    public DemandWithdrawResult demandWithdraw(DemandWithdrawEntity demandWithdrawEntity) {
        String accountID = demandWithdrawEntity.getAccountID();
        String pwd = BankAccountCommon.encodePwd(demandWithdrawEntity.getPwd());
        BigDecimal amount = demandWithdrawEntity.getAmount();
        int agencyID = agencyCommon.getUserAgency();

        // 检查账号密码
        if(publicAccountDao.checkAccountPwd(accountID,pwd) != 1){
            return DemandWithdrawResult.DEMAND_WITHDRAW_FAILED;
        }

        // 检查账户内是否有足够余额
        if(publicAccountDao.getAccountBalance(accountID).compareTo(amount) == -1){
            return DemandWithdrawResult.NO_ENOUGH_BALANCE;
        }

//        // 检查机构是否有足够现金
//        if(agencyDao.getAgencyCash(agencyID).compareTo(amount) == -1){
//            return DemandWithdrawResult.NO_ENOUGH_CASH;
//        }

        // 减少现金
        //agencyDao.removeAgencyCash(amount,agencyID);
        if(trunkCash.removeTellerTrunkCash(amount) == UpdateTrunkCashResult.UPDATE_FAILED){
            return DemandWithdrawResult.NO_ENOUGH_CASH;
        }

        // 减少余额
        publicAccountDao.removeAccountBalance(accountID,amount);

        // 现金账目：减少
        bookCommon.addCashBill(amount.negate(),accountID,"活期取款",true,2);

        // 负债账目：减少
        bookCommon.addFundBill(amount.negate(),accountID,"活期取款",2);

        return DemandWithdrawResult.DEMAND_WITHDRAW_SUCCESS;
    }

    @Override
    public DemandToTimeResult demandToTime(DemandToTimeEntity demandToTimeEntity) {

        String accountID = demandToTimeEntity.getAccountID();
        String pwd = BankAccountCommon.encodePwd(demandToTimeEntity.getPwd());
        BigDecimal amount = demandToTimeEntity.getAmount();

        // 检查账号密码
        if(publicAccountDao.checkAccountPwd(accountID,pwd) != 1){
            return DemandToTimeResult.DEMAND_TO_TIME_FAILED;
        }

        // 检查账户内是否有足够余额
        if(publicAccountDao.getAccountBalance(accountID).compareTo(amount) == -1){
            return DemandToTimeResult.NO_ENOUGH_BALANCE;
        }

        // 减少余额
        publicAccountDao.removeAccountBalance(accountID,amount);

        // 储存时长（月）
        int month = demandToTimeEntity.getMonth();

        // 获取利率
        BigDecimal rate = publicRateDao.getRate(month);

        // 存款日期（今天）
        Date depositDate = new Date();

        // 计算预计取款日期
        Calendar scheduledWithdrawCalendar = Calendar.getInstance();
        scheduledWithdrawCalendar.add(Calendar.MONTH,month);
        Date scheduledWithdrawDate = scheduledWithdrawCalendar.getTime();

        // 记入存款表
        publicTimeDepositDao.addDeposit(accountID,depositDate,rate,amount,scheduledWithdrawDate);

        // 记一笔支出，一笔收入
        bookCommon.addFundBill(amount.negate(),accountID,"活期取款（定活互转）",1);
        bookCommon.addFundBill(amount,accountID,"定期存款（定活互转）",1);

        return DemandToTimeResult.DEMAND_TO_TIME_SUCCESS;
    }
}
