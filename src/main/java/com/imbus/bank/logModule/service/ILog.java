package com.imbus.bank.logModule.service;

import com.imbus.bank.logModule.bo.LogBo;
import com.imbus.bank.logModule.entity.LogEntity;

import java.util.List;

/**
 * Created by zhong on 2020-10-1.
 */
public interface ILog {
    int getLogPage(LogEntity logEntity);

    List<LogBo> getLogList(LogEntity logEntity,int page);
}
