package com.imbus.bank.publicServiceModule.dao;

import com.imbus.bank.publicServiceModule.entity.PublicAccountEntity;
import com.imbus.bank.publicServiceModule.entity.PublicClientEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by zhong on 2020-8-12.
 */
@Repository
@Mapper
public interface PublicClientDao {
    void addClient(@Param("publicClientEntity") PublicClientEntity publicClientEntity);
}
