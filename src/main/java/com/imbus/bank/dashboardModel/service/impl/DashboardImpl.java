package com.imbus.bank.dashboardModel.service.impl;

import com.imbus.bank.dashboardModel.bo.AgencyCashBo;
import com.imbus.bank.dashboardModel.bo.AssetDebtCashBo;
import com.imbus.bank.dashboardModel.bo.BillAmountBo;
import com.imbus.bank.dashboardModel.bo.BillCountBo;
import com.imbus.bank.dashboardModel.dao.DashboardDao;
import com.imbus.bank.dashboardModel.service.IDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2020-11-14.
 */
@Service
public class DashboardImpl implements IDashboard {

    @Autowired
    private DashboardDao dashboardDao;

    @Override
    public AssetDebtCashBo getAssetDebtCash() {
        AssetDebtCashBo assetDebtCashBo = dashboardDao.getAssetDebt();
        assetDebtCashBo.setCash(dashboardDao.getCash());
        return assetDebtCashBo;
    }

    @Override
    public List<BillAmountBo> getBillAmount() {
        return dashboardDao.getBillAmount();
    }

    @Override
    public List<BillCountBo> getBillCount(){
        return dashboardDao.getBillCount();
    }

    @Override
    public List<AgencyCashBo> getAgencyCash(){
        return dashboardDao.getAgencyCash();
    }
}
