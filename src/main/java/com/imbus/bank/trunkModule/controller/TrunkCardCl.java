package com.imbus.bank.trunkModule.controller;

import com.imbus.bank.trunkModule.bo.TrunkCardBo;
import com.imbus.bank.trunkModule.service.ITrunkCard;
import com.imbus.bank.trunkModule.type.TrunkCardResult;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-9-13.
 */
@Api(description = "尾箱银行卡接口")
@RestController
public class TrunkCardCl{
    @Autowired
    private ITrunkCard trunkCard;

    @Transactional
    @RequiresPermissions("trunk:manage")
    @RequestMapping(value = "/api/trunkCard/card",method = RequestMethod.POST)
    public TrunkCardResult addTrunkCard(int trunkID) {
        return trunkCard.addTrunkCard(trunkID);
    }

    @Transactional
    @RequiresPermissions("trunk:manage")
    @RequestMapping(value = "/api/trunkCard/card",method = RequestMethod.DELETE)
    public TrunkCardResult removeTrunkCard(int trunkID, String accountID) {
        return trunkCard.removeTrunkCard(trunkID,accountID);
    }

    @RequiresPermissions("trunk:manage")
    @RequestMapping(value = "/api/trunkCard/card",method = RequestMethod.GET)
    public List<TrunkCardBo> getUserTrunkCard(int userID) {
        return trunkCard.getUserTrunkCard(userID);
    }
}
