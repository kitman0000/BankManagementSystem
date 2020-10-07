package com.imbus.bank.logModule.bo;

import lombok.Data;
import org.apache.poi.ss.formula.ptg.Ptg;

import java.util.Date;

/**
 * Created by zhong on 2020-10-1.
 */
@Data
public class LogBo {
    private int userID;

    private String username;

    private String methodName;

    private String args;

    private Date logTime;
}
