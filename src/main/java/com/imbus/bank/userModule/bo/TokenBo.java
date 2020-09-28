package com.imbus.bank.userModule.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhong on 2019-12-26.
 */
@Data
public class TokenBo {
    private int userID;
    private String username;
    private long createTime;
}
