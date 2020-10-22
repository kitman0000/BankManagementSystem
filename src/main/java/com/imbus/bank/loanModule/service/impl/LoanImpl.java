package com.imbus.bank.loanModule.service.impl;

import com.imbus.bank.common.BankAccountCommon;
import com.imbus.bank.common.BookCommon;
import com.imbus.bank.common.DateUtil;
import com.imbus.bank.common.UserCommon;
import com.imbus.bank.common.dao.BankConfigDao;
import com.imbus.bank.loanModule.bo.LoanApplyBo;
import com.imbus.bank.loanModule.bo.LoanBo;
import com.imbus.bank.loanModule.dao.LoanDao;
import com.imbus.bank.loanModule.dao.LoanRateDao;
import com.imbus.bank.loanModule.dao.LoanSearchDao;
import com.imbus.bank.loanModule.entity.LoanEntity;
import com.imbus.bank.loanModule.service.ILoan;
import com.imbus.bank.loanModule.type.HandleLoanResult;
import com.imbus.bank.loanModule.type.LoanResult;
import com.imbus.bank.loanModule.type.RepaymentResult;
import com.imbus.bank.personalServiceModule.dao.PersonalAccountDao;
import com.imbus.bank.publicServiceModule.dao.PublicAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhong on 2020-8-29.
 */
@Service
public class LoanImpl implements ILoan{
    @Autowired
    private PublicAccountDao publicAccountDao;

    @Autowired
    private PersonalAccountDao personalAccountDao;

    @Autowired
    private BookCommon bookCommon;

    @Autowired
    private LoanRateDao loanRateDao;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private LoanSearchDao loanSearchDao;

    @Autowired
    private BankAccountCommon bankAccountCommon;

    @Autowired
    private BankConfigDao bankConfigDao;

    private final static int PERSONAL = 1;
    private final static int PUBLIC = 2;

    @Override
    public LoanResult requestLoan(LoanEntity loanEntity) {
        int month = loanEntity.getMonth();
        loanEntity.setPwd(BankAccountCommon.encodePwd(loanEntity.getPwd()));

        if(loanEntity.getType() == PERSONAL){
            // 个人贷款
            // 检查密码
            if(personalAccountDao.checkAccountPwd(loanEntity.getAccountID(),loanEntity.getPwd()) != 1){
                return LoanResult.LOAN_FAILED;
            }
            BigDecimal rate = loanRateDao.getPersonalRate(month);
            loanEntity.setRate(rate);

            loanDao.addLoanApply(loanEntity);
        }else if(loanEntity.getType() == PUBLIC){
            // 对公贷款
            // 检查密码
            if(publicAccountDao.checkAccountPwd(loanEntity.getAccountID(),loanEntity.getPwd()) != 1){
                return LoanResult.LOAN_FAILED;
            }

            BigDecimal rate = loanRateDao.getPublicRate(month);
            loanEntity.setRate(rate);

            loanDao.addLoanApply(loanEntity);
        }else {
            return LoanResult.LOAN_FAILED;
        }

        return LoanResult.LOAN_SUCCESS;
    }

    @Override
    public HandleLoanResult handleLoanApply(int id, int result) {

        // 获取贷款详情
        LoanApplyBo loanApplyBo = loanDao.getLoanApplyDetail(id);

        if(loanApplyBo == null||loanApplyBo.getHandleStatus() != 0){
            // 如果存款不存在或者已经处理，则说明id错误
            return HandleLoanResult.HANDLE_FAILED;
        }

        // 检查存款准备金
        BigDecimal totalLoan = loanDao.getTotalLoan();

        // 如果存款准备金不足且同意贷款
        if(totalLoan.add(loanApplyBo.getAmount()).compareTo(bankConfigDao.getDepositReserve()) == 1 && result != 2){
            return HandleLoanResult.NO_DEPOSIT_RESERVE;
        }

        loanDao.handleLoanApply(id,result, UserCommon.getUserBo().getUserID());
        if(result == 2) {
            // 如果拒绝贷款申请，那么直接返回
            return HandleLoanResult.HANDLE_SUCCESS;
        }

        // 账户
        String account = loanApplyBo.getAccountID();

        // 金额
        BigDecimal amount = loanApplyBo.getAmount();

        // 如果同意贷款，计算还款日期
        Calendar scheduledPayment = Calendar.getInstance();

        // 计算还款日期
        scheduledPayment.add(Calendar.MONTH,loanApplyBo.getMonth());

        // 设置贷款日期
        loanApplyBo.setLoanDate(new Date());
        loanApplyBo.setScheduledPayment(scheduledPayment.getTime());

        // 记录贷款表
        loanDao.addLoan(loanApplyBo);

        // 资产端减少
        bookCommon.addFundBill(amount.negate(),account,"贷款",1);

        // 借贷端减少
        bookCommon.addFundBill(amount.negate(),account,"贷款",2);

        // 给用户增加存款
        if(bankAccountCommon.getAccountType(account) == BankAccountCommon.AccountType.PUBLIC){
            publicAccountDao.addAccountBalance(account,amount);
        }else if(bankAccountCommon.getAccountType(account) == BankAccountCommon.AccountType.PRIVATE){
            personalAccountDao.addAccountBalance(account,amount);
        }
        return HandleLoanResult.HANDLE_SUCCESS;
    }

    @Override
    public RepaymentResult repayLoan(int id, String pwd) {
        LoanBo loanBo = loanSearchDao.getLoanDetail(id);
        String accountID = loanBo.getAccount();

        // 账户类型（个人，对公）
        BankAccountCommon.AccountType type = bankAccountCommon.getAccountType(accountID);

        BigDecimal balance = null;

        // 加密密码
        pwd = BankAccountCommon.encodePwd(pwd);

        // 已经还款的贷款
        if(loanBo.getRepaymentStatus() != 0){
            return RepaymentResult.SYSTEM_ERROR;
        }

        if(type == BankAccountCommon.AccountType.PRIVATE){
            // 检查密码
            if(personalAccountDao.checkAccountPwd(accountID,pwd) != 1){
                return RepaymentResult.REPAYMENT_FAILED;
            }

            balance = personalAccountDao.getAccountBalance(accountID);

        }else if(type == BankAccountCommon.AccountType.PUBLIC){
            // 检查密码
            if(publicAccountDao.checkAccountPwd(accountID,pwd) != 1){
                return RepaymentResult.REPAYMENT_FAILED;
            }

            // 余额
            balance = publicAccountDao.getAccountBalance(accountID);

        }else {
            return RepaymentResult.SYSTEM_ERROR;
        }

        // 计算利息
        BigDecimal rate = loanBo.getRate();
        Date yesterday = DateUtil.getZeroOClock();
        Date loanDate = loanBo.getLoanDate();
        long dayGap = (yesterday.getTime() - loanDate.getTime())/(24*60*60*1000);
        BigDecimal interest = rate.multiply(new BigDecimal(dayGap));

        // 本利和
        BigDecimal PAI = loanBo.getAmount().add(interest);

        // 检查余额
        if(balance.compareTo(PAI) == -1){
            return RepaymentResult.NO_ENOUGH_BALANCE;
        }

        // 减少余额
        if(type == BankAccountCommon.AccountType.PRIVATE){
            personalAccountDao.removeAccountBalance(accountID,PAI);
        }else{
            publicAccountDao.removeAccountBalance(accountID,PAI);
        }

        // 资产端：增加
        bookCommon.addFundBill(loanBo.getAmount(),accountID,"还款",1);
        // 借贷端：增加
        bookCommon.addFundBill(PAI,accountID,"还款",2);

        // 修改还款状态
        loanDao.updateLoanRepaymentStatus(id,1);

        return RepaymentResult.REPAYMENT_SUCCESS;
    }
}
