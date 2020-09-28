package com.imbus.bank.agencyModule.entity;


import lombok.Data;

/**
 * Created by zhong on 2020-8-6.
 * 此处暂时不存放现金数，保证数据安全性
 */
@Data
public class AgencyEntity {
    private int id;

    private String name;

    private String bankCode;

    private int city;

    private String address;

    private int ownerID;
}
