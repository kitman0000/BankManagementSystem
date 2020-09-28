package com.imbus.bank.trunkModule.controller;

import com.imbus.bank.trunkModule.bo.TrunkBo;
import com.imbus.bank.trunkModule.entity.TrunkEntity;
import com.imbus.bank.trunkModule.service.ITrunk;
import com.imbus.bank.trunkModule.type.AddTrunkResult;
import com.imbus.bank.trunkModule.type.DeleteTrunkResult;
import com.imbus.bank.trunkModule.type.EditTrunkResult;
import com.imbus.bank.trunkModule.type.SetTellerResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-9-10.
 */
@Api(description = "尾箱管理接口")
@RestController
public class TrunkCl {
    @Autowired
    private ITrunk trunk;

    @RequestMapping(value = "/api/trunk/trunk",method = RequestMethod.POST)
    public AddTrunkResult addTrunk(TrunkEntity trunkEntity) {
        return trunk.addTrunk(trunkEntity);
    }

    @RequestMapping(value = "/api/trunk/trunk",method = RequestMethod.PUT)
    public EditTrunkResult editTrunk(TrunkEntity trunkEntity) {
        return trunk.editTrunk(trunkEntity);
    }

    @RequestMapping(value = "/api/trunk/trunk",method = RequestMethod.DELETE)
    public DeleteTrunkResult deleteTrunk(int id) {
        return trunk.deleteTrunk(id);
    }

    @RequestMapping(value = "/api/trunk/teller",method = RequestMethod.POST)
    public SetTellerResult setTrunkTeller(int id,int userID){
        return trunk.setTrunkTeller(id,userID);
    }

    @ApiOperation(value = "获取当前机构的尾箱")
    @RequestMapping(value = "/api/trunk/trunk",method = RequestMethod.GET)
    public List<TrunkBo> getAgencyTrunk(){
        return trunk.getAgencyTrunk();
    }
}
