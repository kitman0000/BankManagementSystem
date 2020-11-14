package com.imbus.bank.agencyModule.service.impl;

import com.imbus.bank.agencyModule.bo.AgencyBo;
import com.imbus.bank.agencyModule.dao.AgencyDao;
import com.imbus.bank.agencyModule.entity.AgencyEntity;
import com.imbus.bank.agencyModule.bo.CityBo;
import com.imbus.bank.agencyModule.service.IAgency;
import com.imbus.bank.agencyModule.type.AgencyAddType;
import com.imbus.bank.agencyModule.type.AgencyDeleteType;
import com.imbus.bank.agencyModule.type.AgencyEditType;
import com.imbus.bank.utils.PageDivideUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-8-6.
 */
@Service
public class AgencyImpl implements IAgency{
    @Autowired
    private AgencyDao agencyDao;

    final int COUNT_OF_ONE_PAGE = 10;

    @Override
    public AgencyAddType addAgency(AgencyEntity agencyEntity) {
        try {
            agencyDao.insertAgency(agencyEntity);
            return AgencyAddType.AGENCY_ADD_SUCCESS;
        }catch (Exception ex){
            return AgencyAddType.AGENCY_ADD_FAILED;
        }
    }

    @Override
    public int getAgencyPageNumber(String keywords) {
        int number = agencyDao.getAgencyNumbers(keywords);
        return PageDivideUtil.getCountOfPages(number,COUNT_OF_ONE_PAGE);
    }

    @Override
    public List<AgencyBo> getAgencyList(String keywords, int page) {
        int startRow = (page -1) * COUNT_OF_ONE_PAGE;
        return agencyDao.getAgencyList(keywords,startRow,COUNT_OF_ONE_PAGE);
    }

    @Override
    public AgencyDeleteType deleteAgency(int[] agencyID) {
        BigDecimal totalCash = agencyDao.getAgencyTotalCash(agencyID);
        if(totalCash.compareTo(new BigDecimal(0)) !=  0){
            // 机构中仍有现金，拒绝删除
            return AgencyDeleteType.AGENCY_DELETE_FAILED;
        }
        agencyDao.deleteAgency(agencyID);
        return AgencyDeleteType.AGENCY_DELETE_SUCCESS;
    }

    @Override
    public AgencyEditType editAgency(AgencyEntity agencyEntity) {
        agencyDao.editAgency(agencyEntity);
        return AgencyEditType.AGENCY_EDIT_SUCCESS;
    }

    @Override
    public List<CityBo> getCity(String keywords) {
        return agencyDao.getCites(keywords);
    }
}
