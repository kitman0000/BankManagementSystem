package com.imbus.bank.dashboardModel.controller;

import com.imbus.bank.dashboardModel.bo.AgencyCashBo;
import com.imbus.bank.dashboardModel.bo.AssetDebtCashBo;
import com.imbus.bank.dashboardModel.bo.BillAmountBo;
import com.imbus.bank.dashboardModel.bo.BillCountBo;
import com.imbus.bank.dashboardModel.service.IDashboard;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-11-14.
 */
@Api(description = "商务仪表盘接口")
@RestController
public class DashboardController {
    @Autowired
    private IDashboard dashboard;

    @RequestMapping(value = "/api/dashboard/assetDebtCash",method = RequestMethod.GET)
    public AssetDebtCashBo getAssetDebtCash(){
        return dashboard.getAssetDebtCash();
    }

    @RequestMapping(value = "/api/dashboard/billAmount",method = RequestMethod.GET)
    public List<BillAmountBo> getBillAmount(){
        return dashboard.getBillAmount();
    }

    @RequestMapping(value = "/api/dashboard/billCount",method = RequestMethod.GET)
    public List<BillCountBo> getBillCount(){
        return dashboard.getBillCount();
    }

    @RequestMapping(value = "/api/dashboard/agencyCash",method = RequestMethod.GET)
    public List<AgencyCashBo> getAgencyCash(){
        return dashboard.getAgencyCash();
    }
}
