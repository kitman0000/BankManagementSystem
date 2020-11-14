package com.imbus.bank.dashboardModel.service;

import com.imbus.bank.dashboardModel.bo.AgencyCashBo;
import com.imbus.bank.dashboardModel.bo.AssetDebtCashBo;
import com.imbus.bank.dashboardModel.bo.BillAmountBo;
import com.imbus.bank.dashboardModel.bo.BillCountBo;

import java.util.List;

/**
 * Created by zhong on 2020-11-14.
 */
public interface IDashboard {
    AssetDebtCashBo getAssetDebtCash();

    List<BillAmountBo> getBillAmount();

    List<BillCountBo> getBillCount();

    List<AgencyCashBo> getAgencyCash();
}
