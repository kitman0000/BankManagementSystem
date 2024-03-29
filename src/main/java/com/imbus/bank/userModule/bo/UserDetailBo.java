package com.imbus.bank.userModule.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhong on 2019-12-3.
 */
@Data
public class UserDetailBo {
    private int userID;

    private String username;

    private String pwd;

    private String nickName = "";

    private int status;

    private int role;

    private String email = "";

    private String phoneNumber = "";

    private int sex;

    private String birthday = "";

    private String avatarUrl = "";

    private String agencyName;

    private int agencyID;
}
