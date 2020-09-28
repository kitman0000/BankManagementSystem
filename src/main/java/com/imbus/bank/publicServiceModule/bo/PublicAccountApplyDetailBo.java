package com.imbus.bank.publicServiceModule.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhong on 2020-8-14.
 */
@Data
public class PublicAccountApplyDetailBo {
    private String bankAccountID;

    private int id;

    private int clientID;

    private String accountType;

    private int agencyID;

    private String authNo;

    private String name;

    private Date authDate;

    private String pwd;

    private int handleStatus;
}
