package com.imbus.bank.clientSearchModule.bo;

import lombok.Data;

/**
 * Created by zhong on 2020-8-9.
 */
@Data
public class PublicClientBo {
    private int id;

    private String name;

    private String code;

    private String agencyName;

    private String telephone;

    private String authDate;
}
