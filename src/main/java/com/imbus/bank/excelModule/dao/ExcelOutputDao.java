package com.imbus.bank.excelModule.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by zhong on 2020-9-14.
 */
@Mapper
@Repository
public interface ExcelOutputDao {
    List<LinkedHashMap<String,Object>> executeSql(@Param("sql") String sql);
}
