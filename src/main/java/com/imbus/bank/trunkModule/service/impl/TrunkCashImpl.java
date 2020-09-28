package com.imbus.bank.trunkModule.service.impl;

import com.imbus.bank.agencyModule.dao.AgencyDao;
import com.imbus.bank.common.AgencyCommon;
import com.imbus.bank.common.UserCommon;
import com.imbus.bank.trunkModule.dao.TrunkCashDao;
import com.imbus.bank.trunkModule.service.ITrunkCash;
import com.imbus.bank.trunkModule.type.UpdateTrunkCashResult;
import com.imbus.bank.common.BookCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-9-11.
 */
@Service
public class TrunkCashImpl implements ITrunkCash {
    @Autowired
    private BookCommon bookCommon;

    @Autowired
    private AgencyCommon agencyCommon;

    @Autowired
    private AgencyDao agencyDao;

    @Autowired
    private TrunkCashDao trunkCashDao;

    // 从机构到尾箱
    @Override
    public UpdateTrunkCashResult addTrunkCash(int id, BigDecimal amount) {
        int agencyID = agencyCommon.getUserAgency();

        // 如果机构现金不足
        if(agencyDao.getAgencyCash(agencyID).compareTo(amount) == -1){
            return UpdateTrunkCashResult.UPDATE_FAILED;
        }

        // 减少机构现金
        agencyDao.removeAgencyCash(amount,agencyID);
        // 现金端减少
        bookCommon.addCashBill(amount.negate(),"/","机构"+ agencyID+"转移现金到尾箱",true,1);

        // 增加尾箱现金
        trunkCashDao.addTrunkCash(id,amount);
        // 现金端增加
        bookCommon.addCashBill(amount,"/","机构"+ agencyID+"转移现金到尾箱",true,2);
        return UpdateTrunkCashResult.UPDATE_SUCCESS;
    }

    @Override
    public UpdateTrunkCashResult removeTrunkCash(int id, BigDecimal amount) {
        // 如果尾箱现金不足
        if(trunkCashDao.getTrunkCash(id).compareTo(amount) == -1){
            return UpdateTrunkCashResult.UPDATE_FAILED;
        }

        int agencyID = agencyCommon.getUserAgency();

        // 减少尾箱现金
        trunkCashDao.removeTrunkCash(id,amount);
        // 现金端减少
        bookCommon.addCashBill(amount.negate(),"/","尾箱转移现金到机构"+agencyID,true,2);

        // 增加机构现金
        agencyDao.addAgencyCash(amount,agencyID);
        // 现金端增加
        bookCommon.addCashBill(amount,"/","尾箱转移现金到机构"+agencyID,true,1);

        return UpdateTrunkCashResult.UPDATE_SUCCESS;
    }

    /**
     * 添加柜员的尾箱的现金，交易时使用，供外部调用，暂时不会返回错误
     * @param amount 现金数量
     */
    public UpdateTrunkCashResult addTellerTrunkCash(BigDecimal amount){
        int trunkID = getUserTrunk();

        // 增加尾箱现金
        trunkCashDao.addTrunkCash(trunkID,amount);

        return UpdateTrunkCashResult.UPDATE_SUCCESS;
    }

    /**
     * 减少柜员的尾箱的现金，交易时使用，供外部调用,尾箱内现金不足时返回错误
     * @param amount 金额
     */
    public UpdateTrunkCashResult removeTellerTrunkCash(BigDecimal amount){
        int trunkID = getUserTrunk();

        if(trunkCashDao.getTrunkCash(trunkID).compareTo(amount) == -1){
            return UpdateTrunkCashResult.UPDATE_FAILED;
        }

        // 减少尾箱现金
        trunkCashDao.removeTrunkCash(trunkID,amount);

        return UpdateTrunkCashResult.UPDATE_SUCCESS;
    }

    public int getUserTrunk(){
        try {
            int tellerID = UserCommon.getUserBo().getUserID();
            return trunkCashDao.getTellerTrunkID(tellerID);
        }catch (Exception ex){
            return 0;
        }
    }

}
