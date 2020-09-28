package com.imbus.bank.personalServiceModule.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhong on 2020-9-6.
 */
@Data
public class PersonalAccountBo {
    private String id;

    private BigDecimal balance;

    private String accountType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date authDate;

    private boolean available;

    private int status;
}
