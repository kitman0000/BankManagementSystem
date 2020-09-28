package com.imbus.bank.menuModule.bo;

import lombok.Data;

/**
 * Created by zhong on 2019-12-16.
 */
@Data
public class MenuBo {
    private int menuID;
    private int permissionID;
    private String permissionInfo;
    private String permissionName;
    private String menuHref;
    private String menuTitle;
}
