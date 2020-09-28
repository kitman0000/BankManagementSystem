package com.imbus.bank.agencyModule.dao;

import com.imbus.bank.agencyModule.bo.AgencyBo;
import com.imbus.bank.agencyModule.bo.AgencyInfoBo;
import com.imbus.bank.agencyModule.entity.AgencyEntity;
import com.imbus.bank.agencyModule.bo.CityBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-8-6.
 */
@Mapper
@Repository
public interface AgencyDao {
    void insertAgency(@Param("agencyEntity") AgencyEntity agencyEntity);

    int getAgencyNumbers(@Param("keywords") String keywords);

    List<AgencyBo> getAgencyList(@Param("keywords") String keywords, @Param("startRow") int startRow, @Param("rowsOnePage") int rowsOnePage);

    List<CityBo> getCites(@Param("keywords") String keywords);

    void deleteAgency(@Param("agencyID") int[] agencyID);

    BigDecimal getAgencyTotalCash(@Param("agencyID") int[] agencyID);

    void editAgency(@Param("agencyEntity") AgencyEntity agencyEntity);

    int getUserAgencyID(int userID);

    String getAgencyCityCode(int agencyID);

    String getBankCode(int agencyID);

    BigDecimal getAgencyCash(int agencyID);

    void addAgencyCash(@Param("cash") BigDecimal cash, @Param("agencyID") int agencyID);

    void removeAgencyCash(@Param("cash") BigDecimal cash, @Param("agencyID") int agencyID);

    List<AgencyInfoBo> getAllAgency();

    void truncateAgencyBackup();

    void addBackup(@Param("id") int id, @Param("cash") BigDecimal cash);

    List<AgencyInfoBo> getAgencyBackup();

    AgencyBo getAgencyDetail(int agencyID);
}
