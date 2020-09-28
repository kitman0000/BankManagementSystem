package com.imbus.bank.publicServiceModule.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhong on 2020-8-31.
 */

@Data
public class PublicAccountBo {
    private String id;

    private BigDecimal balance;

    private String authNo;

    private String name;

    private String accountType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date authDate;

    private boolean available;

    private int status;
}
