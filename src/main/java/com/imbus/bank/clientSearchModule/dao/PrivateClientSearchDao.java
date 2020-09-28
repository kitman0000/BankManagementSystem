package com.imbus.bank.clientSearchModule.dao;

import com.imbus.bank.clientSearchModule.bo.PrivateClientBo;
import com.imbus.bank.clientSearchModule.bo.PrivateClientDetailBo;
import com.imbus.bank.clientSearchModule.entity.ClientSearchEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhong on 2020-8-9.
 */
@Mapper
@Repository
public interface PrivateClientSearchDao {
    int getPrivateClientPage(@Param("clientSearchEntity") ClientSearchEntity clientSearchEntity);

    List<PrivateClientBo> getPrivateList(@Param("clientSearchEntity") ClientSearchEntity clientSearchEntity, @Param("startRow") int startRow, @Param("rowsOnePage") int rowsOnePage);

    PrivateClientDetailBo getPrivateDetail(int id);
}
