package com.imbus.bank.personalServiceModule.dao;

import com.imbus.bank.personalServiceModule.entity.PersonalClientEntity;
import com.imbus.bank.publicServiceModule.entity.PublicClientEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by zhong on 2020-8-15.
 */
@Repository
@Mapper
public interface PersonalClientDao {
    void addClient(@Param("personalClientEntity") PersonalClientEntity personalClientEntity);
}
