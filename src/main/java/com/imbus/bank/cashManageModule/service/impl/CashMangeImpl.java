package com.imbus.bank.cashManageModule.service.impl;

import com.imbus.bank.agencyModule.dao.AgencyDao;
import com.imbus.bank.cashManageModule.service.ICashManage;
import com.imbus.bank.cashManageModule.type.CashToFundResult;
import com.imbus.bank.cashManageModule.type.FundToCashResult;
import com.imbus.bank.common.AgencyCommon;
import com.imbus.bank.common.BookCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-9-14.
 * 现金管理业务类
 * 现金转资金：减少现金并增加一笔资金账单，反之亦然
 */
@Service
public class CashMangeImpl implements ICashManage{
    @Autowired
    private AgencyDao agencyDao;

    @Autowired
    private BookCommon bookCommon;

    @Override
    public CashToFundResult cashToFund(int agencyID, BigDecimal amount) {
        BigDecimal agencyCash = agencyDao.getAgencyCash(agencyID);
        if(agencyCash.compareTo(amount) == -1){
            // 机构现金不足
            return CashToFundResult.NO_ENOUGH_CASH;
        }

        // 减少机构现金
        agencyDao.removeAgencyCash(amount,agencyID);
        // 现金端：减少
        bookCommon.addCashBill(amount.negate(),"","现金转资金，机构ID"+agencyID,false,1);
        // 资金端：增加资产
        bookCommon.addFundBill(amount,"","现金转资金",1);

        return CashToFundResult.TRANSFER_SUCCESS;
    }

    @Override
    public FundToCashResult fundToCash(int agencyID, BigDecimal amount) {
        // 不考虑资金消耗完的情况

        // 确保机构ID存在
        BigDecimal agencyCash = agencyDao.getAgencyCash(agencyID);
        if(agencyCash == null){
            return FundToCashResult.TRANSFER_FAILED;
        }

        // 资金端：减少资产
        bookCommon.addFundBill(amount.negate(),"","资金转现金",1);
        // 增加机构现金
        agencyDao.addAgencyCash(amount,agencyID);
        // 现金端：增加
        bookCommon.addCashBill(amount,"","资金转现金，机构ID"+agencyID,false,1);
        return FundToCashResult.TRANSFER_SUCCESS;
    }

    @Override
    public BigDecimal getAgencyCash(int agencyID) {
        return agencyDao.getAgencyCash(agencyID);
    }
}
