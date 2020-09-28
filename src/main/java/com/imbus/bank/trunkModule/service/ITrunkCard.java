package com.imbus.bank.trunkModule.service;

import com.imbus.bank.trunkModule.bo.TrunkCardBo;
import com.imbus.bank.trunkModule.type.TrunkCardResult;

import java.util.List;

/**
 * Created by zhong on 2020-9-13.
 */
public interface ITrunkCard {
    TrunkCardResult addTrunkCard(int trunkID);

    TrunkCardResult removeTrunkCard(int trunkID,String accountID);

    List<TrunkCardBo> getUserTrunkCard(int userID);
}
