package com.imbus.bank.trunkModule.service.impl;

import com.imbus.bank.trunkModule.bo.TrunkBo;
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
        trunkDao.deleteTrunk(id);
        return DeleteTrunkResult.DELETE_TRUNK_SUCCESS;
    }

    @Override
    public SetTellerResult setTrunkTeller(int id, int userID) {
        if(trunkCash.getUserTrunk() != 0){
            return SetTellerResult.TELLER_SET_FAILED;
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
