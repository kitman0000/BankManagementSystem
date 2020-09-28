package com.imbus.bank.clientSearchModule.dao;

import com.imbus.bank.clientSearchModule.bo.PrivateClientBo;
import com.imbus.bank.clientSearchModule.bo.PublicClientBo;
import com.imbus.bank.clientSearchModule.bo.PublicClientDetailBo;
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
public interface PublicClientSearchDao {
    int getPublicClientPage(@Param("clientSearchEntity") ClientSearchEntity clientSearchEntity);

    List<PublicClientBo> getPublicList(@Param("clientSearchEntity") ClientSearchEntity clientSearchEntity, @Param("startRow") int startRow, @Param("rowsOnePage") int rowsOnePage);

    PublicClientDetailBo getPublicDetail(int id);
}
