package com.imbus.bank.menuModule.entity;

import lombok.Data;

/**
 * Created by zhong on 2019-12-16.
 */
@Data
public class MenuEntry {
        private int menuID;
        private int permissionID;
        private String menuHref;
        private String menuTitle;

}
