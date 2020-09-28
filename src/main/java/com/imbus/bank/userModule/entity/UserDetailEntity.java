package com.imbus.bank.userModule.entity;

import lombok.Data;

/**
 * Created by zhong on 2019-12-3.
 */
@Data
public class UserDetailEntity {
    private int userID;

    private String pwd;

    private int status;

    private String username;

    private String nickName;

    private int role;

    private String email;

    private String phoneNumber;

    private int sex;

    private String birthday;

    private int agencyID;

}
