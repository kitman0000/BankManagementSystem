package com.imbus.bank.publicServiceModule.bo;

import lombok.Data;

/**
 * Created by zhong on 2020-8-13.
 */
@Data
public class PublicAccountApplyBo {
    private int id;

    private String clientName;

    private int clientID;

    private String accountType;

    private int agencyID;

    private String authNo;

    private String name;

    private String pwd;

    private int handleStatus;
}
