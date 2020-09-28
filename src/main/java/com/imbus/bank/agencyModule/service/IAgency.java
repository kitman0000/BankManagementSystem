package com.imbus.bank.agencyModule.service;

import com.imbus.bank.agencyModule.bo.AgencyBo;
import com.imbus.bank.agencyModule.entity.AgencyEntity;
import com.imbus.bank.agencyModule.bo.CityBo;
import com.imbus.bank.agencyModule.type.AgencyAddType;
import com.imbus.bank.agencyModule.type.AgencyDeleteType;
import com.imbus.bank.agencyModule.type.AgencyEditType;

import java.util.List;

/**
 * Created by zhong on 2020-8-6.
 */
public interface IAgency {
    AgencyAddType addAgency(AgencyEntity agencyEntity);

    int getAgencyPageNumber(String keywords);

    List<AgencyBo> getAgencyList(String keywords, int page);

    AgencyDeleteType deleteAgency(int[] agencyID);

    AgencyEditType editAgency(AgencyEntity agencyEntity);

    List<CityBo> getCity(String keywords);


}
