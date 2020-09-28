package com.imbus.bank.nettingModule.controller;

import com.imbus.bank.nettingModule.bo.NettingBo;
import com.imbus.bank.nettingModule.bo.NettingWarningBo;
import com.imbus.bank.nettingModule.service.INetting;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-9-28.
 */

@Api(description = "轧账查询接口")
@RestController
public class NettingCl {

    @Autowired
    private INetting netting;

    @RequestMapping(value = "/api/netting/page",method = RequestMethod.GET)
    public int getNettingPage(){
        return netting.getNettingPage();
    }

    @RequestMapping(value = "/api/netting/netting",method = RequestMethod.GET)
    public List<NettingBo> getNettingList(int page){
        return netting.getNettingList(page);
    }

    @RequestMapping(value = "/api/netting/warning",method = RequestMethod.GET)
    public List<NettingWarningBo> getNettingWarning(String nettingSign){
        return netting.getNettingWarning(nettingSign);
    }
}
