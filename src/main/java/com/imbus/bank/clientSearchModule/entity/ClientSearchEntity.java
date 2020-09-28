package com.imbus.bank.clientSearchModule.entity;

import lombok.Data;

/**
 * Created by zhong on 2020-8-9.
 */
@Data
public class ClientSearchEntity {
    private String name;

    private String IDNumber;

    private int agencyID;

    private String telephone;

    private String authDateStart;

    private String authDateEnd;
}
