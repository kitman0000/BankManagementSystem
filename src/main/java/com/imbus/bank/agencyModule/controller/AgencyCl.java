package com.imbus.bank.agencyModule.controller;

import com.imbus.bank.agencyModule.bo.AgencyBo;
import com.imbus.bank.agencyModule.entity.AgencyEntity;
import com.imbus.bank.agencyModule.bo.CityBo;
import com.imbus.bank.agencyModule.service.IAgency;
import com.imbus.bank.agencyModule.type.AgencyAddType;
import com.imbus.bank.agencyModule.type.AgencyDeleteType;
import com.imbus.bank.agencyModule.type.AgencyEditType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-8-6.
 *
 * 机构管理接口
 * 机构具有的属性：查看 AgencyEntity
 * 添加，单个删，分页查，修改
 */
@Api(value = "机构管理接口",description = "机构：添加，多个删，分页查，修改   城市：模糊查询")
@RestController
public class AgencyCl {

    @Autowired
    private IAgency agency;

    @ApiOperation(value = "添加机构",notes = "不要传id 返回:AGENCY_ADD_SUCCESS, AGENCY_ADD_FAILED")
    @RequiresPermissions("agency:manage")
    @RequestMapping(value = "api/agency/agency",method = RequestMethod.POST)
    public AgencyAddType addAgency(AgencyEntity agencyEntity){
        return agency.addAgency(agencyEntity);
    }

    @ApiOperation(value = "获取机构页数",notes = "传入机构名与地址关键词模糊搜索，返回页数")
    @RequestMapping(value = "api/agency/agencyPage",method = RequestMethod.GET)
    public int getAgencyPageNumber(String keywords){
       return agency.getAgencyPageNumber(keywords);
    }

    @ApiOperation(value = "获取机构列表",notes = "传入机构名与地址关键词模糊搜索，页数，返回数据列表")
    @RequestMapping(value = "api/agency/agencyList",method = RequestMethod.GET)
    public List<AgencyBo> getAgencyList(String keywords, int page){
        return agency.getAgencyList(keywords,page);
    }

    @Transactional
    @ApiOperation(value = "删除机构",notes = "支持多项删除，当机构有现金时删除失败，返回AGENCY_DELETE_SUCCESS,AGENCY_DELETE_FAILED")
    @RequiresPermissions("agency:manage")
    @RequestMapping(value = "api/agency/agency",method = RequestMethod.DELETE)
    public AgencyDeleteType deleteAgency(int[] agencyID){
        return agency.deleteAgency(agencyID);
    }

    @ApiOperation(value = "编辑机构",notes = "除需要id之外，其他参数与POST方法相同")
    @RequiresPermissions("agency:manage")
    @RequestMapping(value = "api/agency/agency",method = RequestMethod.PUT)
    public AgencyEditType editAgency(AgencyEntity agencyEntity){
        return agency.editAgency(agencyEntity);
    }

    @ApiOperation(value = "获取城市")
    @RequestMapping(value = "api/agency/city",method = RequestMethod.GET)
    public List<CityBo> getCity(String keywords){
        return agency.getCity(keywords);
    }

}
