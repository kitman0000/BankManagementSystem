package com.imbus.bank.common;

import com.imbus.bank.agencyModule.bo.AgencyBo;
import com.imbus.bank.agencyModule.bo.AgencyInfoBo;
import com.imbus.bank.agencyModule.dao.AgencyDao;
import com.imbus.bank.common.bo.BookBo;
import com.imbus.bank.common.dao.BankConfigDao;
import com.imbus.bank.common.dao.BookDao;
import com.imbus.bank.loanModule.dao.LoanDao;
import com.imbus.bank.loanModule.dao.LoanSearchDao;
import com.imbus.bank.nettingModule.dao.NettingDao;
import com.imbus.bank.personalServiceModule.bo.PersonalAccountBo;
import com.imbus.bank.personalServiceModule.dao.PersonalAccountDao;
import com.imbus.bank.publicServiceModule.bo.PublicAccountBo;
import com.imbus.bank.publicServiceModule.dao.PublicAccountDao;
import com.imbus.bank.timeDepositModule.bo.DepositBo;
import com.imbus.bank.timeDepositModule.dao.DepositDao;
import com.imbus.bank.trunkModule.bo.TrunkBo;
import com.imbus.bank.trunkModule.dao.TrunkDao;
import com.imbus.bank.utils.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by zhong on 2020-8-31.
 */
@EnableScheduling
@Component
public class ScheduleService {

    @Autowired
    private BankConfigDao bankConfigDao;

    @Autowired
    private PublicAccountDao publicAccountDao;

    @Autowired
    private BookCommon bookCommon;

    @Autowired
    private DepositDao depositDao;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private LoanSearchDao loanSearchDao;

    @Autowired
    private PersonalAccountDao personalAccountDao;

    @Autowired
    private AgencyDao agencyDao;

    @Autowired
    private TrunkDao trunkDao;

    @Autowired
    private NettingDao nettingDao;

    @Transactional
    //@Scheduled(cron = "0/30 * * * * ? ")
    @Scheduled(cron = "0 0 0 1/1 * ? ")
    public void schedule(){

        // 获取处理的时间段（昨天0点到24点）

        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();

        startTime.set(Calendar.HOUR_OF_DAY,0);
        startTime.set(Calendar.MINUTE,0);
        startTime.set(Calendar.SECOND,0);
        startTime.set(Calendar.MILLISECOND,0);
        startTime.add(Calendar.DATE,-1);

        endTime.setTime(startTime.getTime());
        endTime.add(Calendar.DATE,1);

        /**************** 活期存款利息 ****************/

        // 获取对公账户利息
        List<PublicAccountBo> publicAccountBoList = publicAccountDao.getAccountBalanceList();

        // 对公账户利率
        BigDecimal publicRate = bankConfigDao.getPublicDemandRate();

        for (PublicAccountBo accountBo:publicAccountBoList){
            String accountID = accountBo.getId();

            // 计算利息
            BigDecimal amount = accountBo.getBalance().divide(new BigDecimal(100)).multiply(publicRate).setScale(2, BigDecimal.ROUND_HALF_UP);
            // 添加利息到账户
            publicAccountDao.addAccountBalance(accountID,amount);
            // 借贷端增加
            bookCommon.addSystemFundBill(amount,accountID,"存款利息",2);
        }

        // 获取个人账户利息
        List<PersonalAccountBo> personalAccountBoList = personalAccountDao.getAccountBalanceList();

        // 对公账户利率
        BigDecimal personalRate = bankConfigDao.getPersonalDemandRate();

        for (PersonalAccountBo accountBo:personalAccountBoList){
            String accountID = accountBo.getId();

            // 计算利息
            BigDecimal amount = accountBo.getBalance().divide(new BigDecimal(100)).multiply(personalRate).setScale(2, BigDecimal.ROUND_HALF_UP);
            // 添加利息到账户
            personalAccountDao.addAccountBalance(accountID,amount);

            // 借贷端增加
            bookCommon.addSystemFundBill(amount,accountID,"存款利息",2);
        }

        // 计算资产端数额

        /***************** 统计资产端 *****************/
        BigDecimal lastAsset = bankConfigDao.getLastAsset();

        // 昨天的资产端资金账目
        List<BookBo> fundBookList = bookCommon.getFundBook(1,startTime.getTime(),endTime.getTime());

        // 昨天的资产端现金账目
        List<BookBo> cashBookList = bookCommon.getCashBook(startTime.getTime(),endTime.getTime());

        // 遍历资产端的资金账目和现金账目,计算剩余资产
        for (BookBo book:fundBookList){
            lastAsset = lastAsset.add(book.getAmount());
        }
        for (BookBo book:cashBookList){
            lastAsset = lastAsset.add(book.getAmount());
        }

        // 记录资产
        bankConfigDao.setLastAsset(lastAsset);


        /***************** 统计借贷端 *****************/
        BigDecimal lastDebt = bankConfigDao.getLastDebt();

        // 昨天的借贷端账目
        fundBookList = bookCommon.getFundBook(2,startTime.getTime(),endTime.getTime());

        // 遍历借贷端,计算剩余借贷
        for (BookBo bookBo:fundBookList){
            lastDebt = lastDebt.add(bookBo.getAmount());
        }

        // 记录借贷
        bankConfigDao.setLastDebt(lastDebt);

        /********************** 检查存款 **********************/
        List<DepositBo> depositList = depositDao.getDeposit(startTime.getTime(),endTime.getTime());
        for (DepositBo deposit:depositList){
            if(deposit.getWithDrawStatus() != 0){
                // 如果已经取款，不处理
                continue;
            }

            // 将贷款设为自动延期
            depositDao.updateWithDrawStatus(deposit.getId(),2);

            // 计算利息
            BigDecimal amount = deposit.getAmount().multiply(deposit.getRate().divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_UP);
            // 加上本金
            amount = amount.add(deposit.getAmount());

            // 将本利和再次贷款
            deposit.setAmount(amount);

            // 计算新还款日期
            long timeGap = startTime.getTimeInMillis() - deposit.getDepositDate().getTime();
            deposit.setScheduledWithdrawDate(new Date(endTime.getTimeInMillis() + timeGap));
            // 存款日期设为今日
            deposit.setDepositDate(new Date());

            depositDao.addDeposit(deposit);
        }

        /********************** 检查贷款 **********************/

        // 获取未偿还的贷款
        List<Integer> loanIDList = loanSearchDao.getUnpaidLoanByDate(startTime.getTime(),endTime.getTime());
        for(int loanID:loanIDList){
            loanDao.updateLoanRepaymentStatus(loanID,2);
        }


        /********************** 计算存款准备金 *********************/
        // 存款准备金率
        BigDecimal depositReserveRate = bankConfigDao.getDepositReserveRate();

        // 获取所有存款
        BigDecimal publicTotal = bankConfigDao.getPublicTotalBalance();
        if(publicTotal == null){
            publicTotal = new BigDecimal(0);
        }
        BigDecimal personalTotal = bankConfigDao.getPersonalTotalBalance();
        if(personalTotal == null){
            personalTotal = new BigDecimal(0);
        }
        BigDecimal timeDepositTotal = bankConfigDao.getDepositTotal();
        if(timeDepositTotal == null){
            timeDepositTotal = new BigDecimal(0);
        }

        BigDecimal depositTotal = publicTotal.add(personalTotal).add(timeDepositTotal);

        // 存款准备金
        BigDecimal depositReserve = depositTotal.multiply(depositReserveRate).setScale(2, BigDecimal.ROUND_HALF_UP);
        bankConfigDao.setDepositReserve(depositReserve);

        netting(cashBookList,startTime.getTime(),endTime.getTime());
        syncAgencyAndTrunk();
    }

    /***
     * 轧账
     * 1、获取机构、尾箱的备份现金数据
     * 2、将当日交易信息通过备份数据计算
     * 3、将当前现金数据和计算后的备份数据相比较，相等既平账
     */
    private void netting(List<BookBo> cashList,Date startTime,Date endTime){
        // 获取备份数据

        // 获取机构备份表
        List<AgencyInfoBo> agencyBackupList = agencyDao.getAgencyBackup();
        // 将机构数据转换为HashMap
        HashMap<Integer,AgencyInfoBo> agencyBackupHashMap = new HashMap<>();
        for (AgencyInfoBo agency:agencyBackupList){
            agencyBackupHashMap.put(agency.getId(),agency);
        }

        // 获取尾箱备份表
        List<TrunkBo> trunkBackupList = trunkDao.getTrunkBackup();
        HashMap<Integer,TrunkBo> trunkBackupHashMap = new HashMap<>();
        for(TrunkBo trunkBo:trunkBackupList){
            trunkBackupHashMap.put(trunkBo.getId(),trunkBo);
        }

        // 轧账结果
        boolean netResult = true;
        // 现金总额
        BigDecimal totalAmount = new BigDecimal(0);
        // 轧账签名
        String nettingSign = EncodeUtil.encodeMd5(DateUtil.getTimestamp());

        for (BookBo book:cashList){
            totalAmount = totalAmount.add(book.getAmount());

            if(book.getCashObject() == 1){
                // 如果是机构的账单
                AgencyInfoBo agency = agencyBackupHashMap.get(book.getAgencyID());
                BigDecimal cash = agency.getCash();
                agency.setCash(cash.add(book.getAmount()));
                agencyBackupHashMap.put(agency.getId(),agency);

            }else if(book.getCashObject() == 2){
                // 如果是尾箱的账单
                TrunkBo trunk = trunkBackupHashMap.get(book.getTrunkID());
                BigDecimal cash = trunk.getCash();
                trunk.setCash(cash.add(book.getAmount()));
                trunkBackupHashMap.put(trunk.getId(),trunk);
            }
        }

        List<AgencyInfoBo> agencyList = agencyDao.getAllAgency();
        List<TrunkBo> trunkList = trunkDao.getAllTrunk();

        // 将当前的机构和计算后的做比较
        for (AgencyInfoBo agencyNow:agencyList){
            AgencyInfoBo agencyBackup = agencyBackupHashMap.get(agencyNow.getId());
            if(agencyBackup == null){
                continue;
            }
            if(agencyBackup.getCash().compareTo(agencyNow.getCash()) != 0){
                String agencyName = agencyDao.getAgencyDetail(agencyBackup.getId()).getName();
                nettingDao.addNettingWarning("机构"+agencyName,agencyBackup.getCash(),agencyNow.getCash(),nettingSign);
                netResult = false;
            }
        }

        // 将当前的尾箱和计算后的做比较
        for (TrunkBo trunkNow:trunkList) {
            TrunkBo trunkBackup = trunkBackupHashMap.get(trunkNow.getId());
            if(trunkBackup == null){
                continue;
            }
            if(trunkBackup.getCash().compareTo(trunkNow.getCash()) != 0){
                nettingDao.addNettingWarning("尾箱编号" + trunkBackup.getNumber(),trunkBackup.getCash(),trunkNow.getCash(),nettingSign);
                netResult = false;
            }
        }


        nettingDao.addNettingResult(new Date(),netResult,cashList.size(),totalAmount,startTime,endTime,nettingSign);
    }

    /***
     * 同步数据到backup表
     */
    private void syncAgencyAndTrunk(){

        // 获取所有机构
        List<AgencyInfoBo> agencyList = agencyDao.getAllAgency();

        // 重置备份表
        agencyDao.truncateAgencyBackup();

        // 同步机构数据
        for (AgencyInfoBo agency:agencyList){
            agencyDao.addBackup(agency.getId(),agency.getCash());
        }

        // 获取所有尾箱
        List<TrunkBo> trunkList = trunkDao.getAllTrunk();

        // 重置备份表
        trunkDao.truncateTrunkBackup();

        // 同步尾箱数据
        for (TrunkBo trunk:trunkList){
            trunkDao.addTrunkBackup(trunk.getId(),trunk.getAgencyID(),trunk.getCash());
        }


    }
}
