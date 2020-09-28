package com.imbus.bank.clientSearchModule.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-8-10.
 */
@Data
public class PublicClientDetailBo {
    private int id;

    private String name;

    private String address;

    private String postalCode;

    private int clientType;

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

    private String code;

    private String agencyName;

    private String telephone;

    private String authDate;

    private boolean available;


}
