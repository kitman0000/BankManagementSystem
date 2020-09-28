package com.imbus.bank.personalServiceModule.entity;

import com.imbus.bank.componet.Entity.ClientEntity;
import lombok.Data;

@Data
public class PersonalClientEntity extends ClientEntity {
    private int id;

    private String name;

    private int credentialsType;

    private String credentialsNumber;

    private String credentialsLocation;

    private String nation;

    private int agencyID;

    private String address;

    private String telephoneNum;

    private String mobilePhoneNumber;

    private String postalCode;

    private String note;

    private String authDate;

    private boolean available;
}
