package com.imbus.bank.publicServiceModule.dao;

import com.imbus.bank.publicServiceModule.bo.PublicAccountApplyBo;
import com.imbus.bank.publicServiceModule.bo.PublicAccountApplyDetailBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhong on 2020-8-13.
 */
@Mapper
@Repository
public interface PublicAccountApplyDao {
    int getApplyCount(int agencyID);

    List<PublicAccountApplyBo> getApply(@Param("agencyID") int agencyID, @Param("startRow") int startRow, @Param("rowsOnePage") int rowsOnePage);

    void updateApplyStatus(@Param("id") int id, @Param("status") int status, @Param("userID") int userID, @Param("authNo") String authNo);

    PublicAccountApplyDetailBo getApplyDetail(int id);
}
