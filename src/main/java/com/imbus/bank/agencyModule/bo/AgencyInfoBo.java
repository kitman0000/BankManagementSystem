package com.imbus.bank.agencyModule.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhong on 2020-9-23.
 * 本类包含机构id和cash数据，主要用于轧账，查询请使用AgencyBo
 */
@Data
public class AgencyInfoBo {
    private int id;

    private BigDecimal cash;
}
