package com.imbus.bank.personalServiceModule.entity;

import com.imbus.bank.componet.Entity.AccountEntity;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class PersonalAccountEntity extends AccountEntity {
    private String id;

    private int clientID;

    private String accountType;

    private String authDate;

    private int agencyID;

    private String pwd;

    private boolean available;

    private BigDecimal balance;
}
