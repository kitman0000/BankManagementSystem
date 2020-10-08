package com.imbus.bank.logModule.dao;

import com.imbus.bank.logModule.bo.LogBo;
import com.imbus.bank.logModule.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by zhong on 2020-10-1.
 */
@Repository
@Mapper
public interface LogDao {
    void addLogInfo(@Param("userID") int userID, @Param("methodName") String methodName, @Param("args") String args);

    int getLogPage(@Param("logEntity") LogEntity logEntity);

    List<LogBo> getLog(@Param("logEntity") LogEntity logEntity, @Param("startRow") int startRow, @Param("rowsOnePage") int rowsOnePage);
}
