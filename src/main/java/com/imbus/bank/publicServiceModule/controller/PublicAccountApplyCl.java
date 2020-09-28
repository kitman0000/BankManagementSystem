package com.imbus.bank.publicServiceModule.controller;

import com.imbus.bank.publicServiceModule.bo.PublicAccountApplyBo;
import com.imbus.bank.publicServiceModule.service.IPublicAccountApply;
import com.imbus.bank.publicServiceModule.type.HandlePublicAccountApplyResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-8-13.
 */
@Api(description = "对公账户开户申请接口")
@RestController
public class PublicAccountApplyCl{
    @Autowired
    private IPublicAccountApply publicAccountApply;

    @RequestMapping(value = "/api/publicAccountApply/page",method = RequestMethod.GET)
    public int getApplyPage() {
        return publicAccountApply.getApplyPage();
    }

    @RequestMapping(value = "/api/publicAccountApply/ApplyList",method = RequestMethod.GET)
    public List<PublicAccountApplyBo> getApply(int page) {
        return publicAccountApply.getApply(page);
    }

    @Transactional
    @RequestMapping(value = "/api/publicAccountApply/apply",method = RequestMethod.POST)
    public HandlePublicAccountApplyResult handlePublicAccountApply(int id,int result){
        return publicAccountApply.handleAccountApply(id,result);
    }
}
