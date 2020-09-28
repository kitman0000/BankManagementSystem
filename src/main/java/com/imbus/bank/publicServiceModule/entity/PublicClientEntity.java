package com.imbus.bank.publicServiceModule.entity;

import com.imbus.bank.componet.Entity.ClientEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhong on 2020-8-11.
 */
@Data
public class PublicClientEntity extends ClientEntity {
    private int id;

    private String name;

    private String telephone;

    private String address;

    private String postalCode;

    private int clientType;

    private String code;

    private int personType;

    private String personName;

    private int personCredentialsType;

    private String personCredentialsNumber;

    private String industryType;

    private BigDecimal regCapital;

    private String areaCode;

    private String businessScope;

    private String certifiedDocuments;

    private String taxRegCertificateNum;

    private int agencyID;

    private Date authDate;
}
