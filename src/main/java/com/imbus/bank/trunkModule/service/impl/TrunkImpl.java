package com.imbus.bank.trunkModule.service.impl;

import com.imbus.bank.trunkModule.bo.TrunkBo;
import com.imbus.bank.trunkModule.dao.TrunkCashDao;
import com.imbus.bank.trunkModule.dao.TrunkDao;
import com.imbus.bank.trunkModule.entity.TrunkEntity;
import com.imbus.bank.trunkModule.service.ITrunk;
import com.imbus.bank.trunkModule.type.AddTrunkResult;
import com.imbus.bank.trunkModule.type.DeleteTrunkResult;
import com.imbus.bank.trunkModule.type.EditTrunkResult;
import com.imbus.bank.trunkModule.type.SetTellerResult;
import com.imbus.bank.common.AgencyCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-9-10.
 */
@Service
public class TrunkImpl implements ITrunk {
    @Autowired
    private TrunkDao trunkDao;

    @Autowired
    private TrunkCashImpl trunkCash;

    @Autowired
    private AgencyCommon agencyCommon;

    @Autowired
    private TrunkCashDao trunkCashDao;

    @Override
    public AddTrunkResult addTrunk(TrunkEntity trunkEntity) {
        int agencyID = agencyCommon.getUserAgency();
        trunkDao.addTrunk(trunkEntity,agencyID);

        return AddTrunkResult.ADD_TRUNK_SUCCESS;
    }

    @Override
    public EditTrunkResult editTrunk(TrunkEntity trunkEntity) {
        trunkDao.editTrunk(trunkEntity);
        return EditTrunkResult.EDIT_TRUNK_SUCCESS;
    }

    @Override
    public DeleteTrunkResult deleteTrunk(int id) {
        BigDecimal trunkCash = trunkCashDao.getTrunkCash(id);
        if(trunkCash.compareTo(new BigDecimal(0)) != 0){
            return DeleteTrunkResult.DELETE_TRUNK_FAILED;
        }

        trunkDao.deleteTrunk(id);
        return DeleteTrunkResult.DELETE_TRUNK_SUCCESS;
    }

    @Override
    public SetTellerResult setTrunkTeller(int id, int userID) {
        try {
            trunkCashDao.getTellerTrunkID(userID);
            // 如果程序没有抛出异常，则柜员有尾箱，返回失败
            return SetTellerResult.TELLER_SET_FAILED;
        }catch (Exception ex) {
            // 如果抛出异常，则柜员没有尾箱，可以继续
        }
        trunkDao.setTrunkTeller(id,userID);
        return SetTellerResult.TELLER_SET_SUCCESS;
    }

    @Override
    public List<TrunkBo> getAgencyTrunk() {
        int agencyID = agencyCommon.getUserAgency();
        return trunkDao.getAgencyTrunk(agencyID);
    }
}
