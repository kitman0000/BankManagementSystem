package com.imbus.bank.trunkModule.dao;

import com.imbus.bank.trunkModule.bo.TrunkBo;
import com.imbus.bank.trunkModule.entity.TrunkEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-9-10.
 */
@Mapper
@Repository
public interface TrunkDao {
    void addTrunk(@Param("trunkEntity") TrunkEntity trunkEntity, @Param("agencyID") int agencyID);

    void editTrunk(@Param("trunkEntity") TrunkEntity trunkEntity);

    void deleteTrunk(int id);

    void setTrunkTeller(@Param("id") int id, @Param("tellerID") int tellerID);

    List<TrunkBo> getAgencyTrunk(int agencyID);

    List<TrunkBo> getAllTrunk();

    void truncateTrunkBackup();

    void addTrunkBackup(@Param("id") int id, @Param("agencyID") int agencyID, @Param("cash") BigDecimal cash);

    List<TrunkBo> getTrunkBackup();
}
