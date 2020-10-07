package com.imbus.bank.logModule.controller;

import com.imbus.bank.logModule.bo.LogBo;
import com.imbus.bank.logModule.entity.LogEntity;
import com.imbus.bank.logModule.service.ILog;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2020-10-1.
 */
@Api(description = "日志查询接口")
@RestController
public class LogSearchCl {
    @Autowired
    private ILog log;

    @RequestMapping(value = "/api/log/page",method = RequestMethod.GET)
    public int getLogPage(LogEntity logEntity) {
        return log.getLogPage(logEntity);
    }

    @RequestMapping(value = "/api/log/list",method = RequestMethod.GET)
    public List<LogBo> getLogList(LogEntity logEntity, int page) {
        return log.getLogList(logEntity,page);
    }
}
