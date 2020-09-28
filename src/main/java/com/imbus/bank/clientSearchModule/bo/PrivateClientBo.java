package com.imbus.bank.clientSearchModule.bo;

import lombok.Data;

/**
 * Created by zhong on 2020-8-9.
 */
@Data
public class PrivateClientBo {
    private int id;

    private String name;

    private String credentialsNumber;

    private String agencyName;

    private String telephoneNum;

    private String mobilePhoneNumber;

    private String authDate;
}
