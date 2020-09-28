package com.imbus.bank.publicServiceModule.service.impl;

import com.imbus.bank.agencyModule.dao.AgencyDao;
import com.imbus.bank.common.AgencyCommon;
import com.imbus.bank.common.BankAccountCommon;
import com.imbus.bank.common.BookCommon;
import com.imbus.bank.common.DateUtil;
import com.imbus.bank.common.dao.BankConfigDao;
import com.imbus.bank.componet.Entity.TimeDepositEntity;
import com.imbus.bank.componet.Entity.TimeToDemandEntity;
import com.imbus.bank.componet.Entity.TimeWithdrawEntity;
import com.imbus.bank.componet.bo.TimeWithdrawBo;
import com.imbus.bank.componet.bo.TimeDepositItemBo;
import com.imbus.bank.componet.type.TimeDepositResult;
import com.imbus.bank.componet.bo.TimeToDemandBo;
import com.imbus.bank.publicServiceModule.dao.PublicAccountDao;
import com.imbus.bank.publicServiceModule.dao.PublicRateDao;
import com.imbus.bank.publicServiceModule.dao.PublicTimeDepositDao;
import com.imbus.bank.publicServiceModule.service.IPublicTime;
import com.imbus.bank.publicServiceModule.type.TimeWithdrawResult;
import com.imbus.bank.trunkModule.dao.TrunkCashDao;
import com.imbus.bank.trunkModule.service.impl.TrunkCashImpl;
import com.imbus.bank.trunkModule.type.UpdateTrunkCashResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhong on 2020-8-20.
 */
@Service
public class PublicTimeImpl implements IPublicTime{

    @Autowired
    private PublicTimeDepositDao publicTimeDepositDao;

    @Autowired
    private PublicRateDao publicRateDao;

    @Autowired
    private PublicAccountDao publicAccountDao;

    @Autowired
    private AgencyDao agencyDao;

    @Autowired
    private AgencyCommon agencyCommon;

    @Autowired
    private BookCommon bookCommon;

    @Autowired
    private BankConfigDao bankConfigDao;

    @Autowired
    private TrunkCashImpl trunkCash;

    @Override
    public TimeDepositResult timeDeposit(TimeDepositEntity timeDepositEntity) {
        String accountID = timeDepositEntity.getAccountID();
        String pwd = BankAccountCommon.encodePwd(timeDepositEntity.getPwd());

        // 检查账号密码
        if(publicAccountDao.checkAccountPwd(accountID,pwd) != 1){
            return TimeDepositResult.TIME_DEPOSIT_FAILED;
        }

        // 储存时长（月）
        int month = timeDepositEntity.getMonth();

        // 获取利率
        BigDecimal rate = publicRateDao.getRate(month);

        // 本金
        BigDecimal amount = timeDepositEntity.getAmount();

        // 存款日期（今天）
        Date depositDate = new Date();

        // 计算预计取款日期
        Calendar scheduledWithdrawCalendar = Calendar.getInstance();
        scheduledWithdrawCalendar.add(Calendar.MONTH,month);
        Date scheduledWithdrawDate = scheduledWithdrawCalendar.getTime();

        // 机构号
        int agencyID = agencyCommon.getUserAgency();

        // 增加现金
//        agencyDao.addAgencyCash(amount,agencyID);
        trunkCash.addTellerTrunkCash(amount);

        // 记入存款表
        publicTimeDepositDao.addDeposit(accountID,depositDate,rate,amount,scheduledWithdrawDate);

        // 现金账目：增加
        bookCommon.addCashBill(amount,accountID,"定期存款",true,2);

        // 负债账目：增加
        bookCommon.addFundBill(amount,accountID,"定期存款",2);

        return TimeDepositResult.TIME_DEPOSIT_SUCCESS;
    }

    @Override
    public TimeWithdrawBo timeWithdraw(TimeWithdrawEntity timeWithdrawEntity) {
        int depositID = timeWithdrawEntity.getId();
        String pwd = BankAccountCommon.encodePwd(timeWithdrawEntity.getPwd());
        String accountID = timeWithdrawEntity.getAccount();

        TimeWithdrawBo timeWithdrawBo = getWithdrawBo(accountID,depositID,pwd);

        // 如果取款成功，减少现金
        if(timeWithdrawBo.getResult() == TimeWithdrawResult.WITHDRAW_SUCCESS){
            // 减少现金
            if(trunkCash.removeTellerTrunkCash(timeWithdrawBo.getAmount()) == UpdateTrunkCashResult.UPDATE_FAILED){
                timeWithdrawBo.setResult(TimeWithdrawResult.NO_ENOUGH_CASH);
                return timeWithdrawBo;
            }

            // 现金账目：减少取款额
            bookCommon.addCashBill(timeWithdrawBo.getAmount().negate(),accountID,"定期取款",true,2);

            // 负债账目：减少存款额
            bookCommon.addFundBill(timeWithdrawBo.getDepositAmount().negate(),accountID,"定期取款",2);
        }

        return timeWithdrawBo;
    }

    @Override
    public List<TimeDepositItemBo> getTimeDeposit(String accountID) {
        return publicTimeDepositDao.getDepositList(accountID);
    }

    @Override
    public TimeToDemandBo timeToDemand(TimeToDemandEntity timeToDemandEntity) {
        int depositID = timeToDemandEntity.getId();
        String pwd = BankAccountCommon.encodePwd(timeToDemandEntity.getPwd());
        String accountID = timeToDemandEntity.getAccount();

        TimeWithdrawBo timeWithdrawBo = getWithdrawBo(accountID,depositID,pwd);

        TimeToDemandBo timeToDemandBo = new TimeToDemandBo();

        if(timeWithdrawBo.getResult() != TimeWithdrawResult.WITHDRAW_SUCCESS){
            timeToDemandBo.setResult(timeWithdrawBo.getResult());
            return timeToDemandBo;
        }

        BigDecimal amount = timeWithdrawBo.getAmount();
        TimeWithdrawResult result = timeWithdrawBo.getResult();

        publicAccountDao.addAccountBalance(accountID,amount);

        // 资金账目：减少
        bookCommon.addFundBill(amount.negate(),accountID,"定期取款（定活互转）",1);

        // 负债账目：减少贷款额
        bookCommon.addFundBill(timeWithdrawBo.getDepositAmount().negate(),accountID,"定期取款（定活互转）",2);

        // 资金账目：增加
        bookCommon.addFundBill(amount,accountID,"活期存款（定活互转）",1);

        // 负债账目：增加
        bookCommon.addFundBill(amount,accountID,"活期存款（定活互转）",2);

        timeToDemandBo.setAmount(amount);
        timeToDemandBo.setResult(result);
        timeToDemandBo.setWithdrawType(timeWithdrawBo.getWithdrawType());

        return timeToDemandBo;
    }

    private TimeWithdrawBo getWithdrawBo(String accountID,int depositID,String pwd){
        TimeWithdrawBo timeWithdrawBo = new TimeWithdrawBo();

        TimeDepositItemBo timeDepositItemBo = publicTimeDepositDao.getDeposit(depositID);

        // 检查存款是否已被取出
        if(timeDepositItemBo == null || timeDepositItemBo.getWithDrawStatus() != 0){
            timeWithdrawBo.setResult(TimeWithdrawResult.ACCOUNT_ERROR);
            return timeWithdrawBo;
        }

        // 检查账号是否符合
        if(!accountID.equals(timeDepositItemBo.getAccountID())){
            timeWithdrawBo.setResult(TimeWithdrawResult.ACCOUNT_ERROR);
            return timeWithdrawBo;
        }

        // 检查密码
        if(publicAccountDao.checkAccountPwd(accountID,pwd) != 1){
            timeWithdrawBo.setResult(TimeWithdrawResult.WITHDRAW_FAILED);
            return timeWithdrawBo;
        }

        // 贷款数额
        BigDecimal depositAmount = timeDepositItemBo.getAmount();
        // 取出数额
        BigDecimal withdrawAmount;

        Date yesterday = DateUtil.getZeroOClock();

        if(yesterday.getTime() < timeDepositItemBo.getScheduledWithdrawDate().getTime()){
            // 提前支取
            BigDecimal rate = bankConfigDao.getPublicDemandRate();

            // 存款时长
            long day = (yesterday.getTime() - timeDepositItemBo.getDepositDate().getTime()) /(24*60*60*1000);

            withdrawAmount = depositAmount.multiply(rate).divide(new BigDecimal(100)).multiply(new BigDecimal(day)).setScale(2, BigDecimal.ROUND_HALF_UP);

            timeWithdrawBo.setWithdrawType(3);
            publicTimeDepositDao.updateTimeDepositStatus(depositID,3);
        }else {
            // 到期支取
            withdrawAmount = depositAmount.multiply(timeDepositItemBo.getRate().divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_UP);;
            timeWithdrawBo.setWithdrawType(1);
            publicTimeDepositDao.updateTimeDepositStatus(depositID,1);
        }

        // 加上本金
        withdrawAmount = withdrawAmount.add(depositAmount);

        timeWithdrawBo.setResult(TimeWithdrawResult.WITHDRAW_SUCCESS);
        timeWithdrawBo.setAmount(withdrawAmount);
        timeWithdrawBo.setDepositAmount(depositAmount);

        return timeWithdrawBo;
    }


}
