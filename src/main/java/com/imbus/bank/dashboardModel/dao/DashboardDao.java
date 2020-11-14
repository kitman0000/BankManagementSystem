package com.imbus.bank.dashboardModel.dao;

import com.imbus.bank.dashboardModel.bo.AgencyCashBo;
import com.imbus.bank.dashboardModel.bo.AssetDebtCashBo;
import com.imbus.bank.dashboardModel.bo.BillAmountBo;
import com.imbus.bank.dashboardModel.bo.BillCountBo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-11-14.
 */
@Mapper
@Repository
public interface DashboardDao {
    AssetDebtCashBo getAssetDebt();

    BigDecimal getCash();

    List<BillAmountBo> getBillAmount();

    List<BillCountBo> getBillCount();

    List<AgencyCashBo> getAgencyCash();
}
