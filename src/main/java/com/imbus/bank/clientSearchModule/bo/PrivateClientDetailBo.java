package com.imbus.bank.clientSearchModule.bo;

import lombok.Data;

/**
 * Created by zhong on 2020-8-10.
 */
@Data
public class PrivateClientDetailBo {
    private int id;

    private String name;

    private int credentialsType;

    private String credentialsNumber;

    private String credentialsLocation;

    private String nation;

    private String address;

    private String agencyName;

    private String telephoneNum;

    private String mobilePhoneNumber;

    private String postalCode;

    private String note;

    private String authDate;

    private boolean available;

}
