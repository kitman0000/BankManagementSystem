package com.imbus.bank.logModule.service.impl;

import com.imbus.bank.logModule.bo.LogBo;
import com.imbus.bank.logModule.dao.LogDao;
import com.imbus.bank.logModule.entity.LogEntity;
import com.imbus.bank.logModule.service.ILog;
import com.imbus.bank.utils.PageDivideUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2020-10-1.
 */
@Service
public class LogImpl implements ILog {
    @Autowired
    private LogDao logDao;

    private final int ROWS_ONE_PAGE = 20;

    @Override
    public int getLogPage(LogEntity logEntity) {
        int amount = logDao.getLogPage(logEntity);
        return PageDivideUtil.getCountOfPages(amount,ROWS_ONE_PAGE);
    }

    @Override
    public List<LogBo> getLogList(LogEntity logEntity, int page) {
        int startRow = (page -1) * ROWS_ONE_PAGE;
        return logDao.getLog(logEntity,startRow,ROWS_ONE_PAGE);
    }
}
