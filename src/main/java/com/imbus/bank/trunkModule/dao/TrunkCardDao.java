package com.imbus.bank.trunkModule.dao;

import com.imbus.bank.trunkModule.bo.TrunkCardBo;
import com.imbus.bank.trunkModule.type.TrunkCardResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhong on 2020-9-13.
 */
@Mapper
@Repository
public interface TrunkCardDao {
    void addTrunkCard(@Param("trunkID") int trunkID, @Param("accountID") String accountID);

    void removeTrunkCard(@Param("trunkID") int trunkID, @Param("accountID") String accountID);

    List<TrunkCardBo> getUserTrunkCard(int trunkID);

    String selectTrunkFirstClassCard(@Param("trunkID") int trunkID);

    String selectTrunkSecondClassCard(@Param("trunkID") int trunkID);


}
