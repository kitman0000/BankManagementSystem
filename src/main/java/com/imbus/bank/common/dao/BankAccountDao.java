package com.imbus.bank.common.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by zhong on 2020-8-14.
 */
@Mapper
@Repository
public interface BankAccountDao {
    String getLastAutoNumber(@Param("binCode") String binCode, @Param("areaCode") String areaCode, @Param("bankCode") String bankCode);

    void insertAutoNumber(@Param("binCode") String binCode, @Param("areaCode") String areaCode,
                          @Param("bankCode") String bankCode, @Param("autoNumber") String autoNumber);
}
