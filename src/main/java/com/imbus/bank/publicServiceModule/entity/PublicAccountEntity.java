package com.imbus.bank.publicServiceModule.entity;

import com.imbus.bank.componet.Entity.AccountEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-8-11.
 */
@Data
public class PublicAccountEntity extends AccountEntity{
    private String id;

    private int clientID;

    private String accountType;

    int agencyID;

    String authDate;

    String authNo;

    String name;

    BigDecimal balance;

    String pwd;
}
