package com.imbus.bank.agencyModule.bo;


import lombok.Data;

/**
 * Created by zhong on 2020-8-6.
 * 此处暂时不存放现金数，保证数据安全性
 */
@Data
public class AgencyBo {
    private int id;

    private String name;

    private String bankCode;

    private int city;

    private String cityName;

    private String ownerName;

    private String address;

    private int ownerID;
}
