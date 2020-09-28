package com.imbus.bank.menuModule.service;

import com.imbus.bank.menuModule.bo.MenuBo;
import com.imbus.bank.menuModule.bo.ParentMenuBo;
import com.imbus.bank.menuModule.entity.MenuEntry;

import java.util.List;

/**
 * Created by zhong on 2019-12-16.
 */
public interface IMenu {
    String setMenu(MenuEntry menu);

    List<MenuBo> getMenu();

    List<ParentMenuBo> getUserMenu();
}
