package com.imbus.bank.menuModule.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by zhong on 2020-1-10.
 */
@Data
public class ParentMenuBo {
    private int parentMenuID;
    private String title;
    private List<MenuBo> menuBoList;
}
