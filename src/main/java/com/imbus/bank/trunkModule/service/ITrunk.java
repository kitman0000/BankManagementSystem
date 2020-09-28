package com.imbus.bank.trunkModule.service;

import com.imbus.bank.trunkModule.bo.TrunkBo;
import com.imbus.bank.trunkModule.entity.TrunkEntity;
import com.imbus.bank.trunkModule.type.*;

import java.util.List;

/**
 * Created by zhong on 2020-9-10.
 */
public interface ITrunk {
    AddTrunkResult addTrunk(TrunkEntity trunkEntity);

    EditTrunkResult editTrunk(TrunkEntity trunkEntity);

    DeleteTrunkResult deleteTrunk(int id);

    SetTellerResult setTrunkTeller(int id,int userID);

    List<TrunkBo> getAgencyTrunk();
}
