package com.imbus.bank.excelModule.controller;

import com.imbus.bank.excelModule.service.IExcelOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2020-9-14.
 */
@Api(value = "Excel导出接口")
@RestController
public class ExcelOutputCl {
    @Autowired
    private IExcelOutput excelOutput;

    @RequiresPermissions("excel:export")
    @ApiOperation(value = "导出SQL EXCEL文档")
    @RequestMapping(value = "/api/excel/excel",method = RequestMethod.GET)
    public String getExcelOutput(String sql) {
        return excelOutput.getExcelOutput(sql);
    }
}
