package com.imbus.bank.menuModule.controller;

import com.imbus.bank.menuModule.bo.MenuBo;
import com.imbus.bank.menuModule.bo.ParentMenuBo;
import com.imbus.bank.menuModule.entity.MenuEntry;
import com.imbus.bank.menuModule.service.IMenu;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2019-12-16.
 */
@RestController
public class MenuCl {
    @Autowired
    IMenu menu;

    @RequestMapping(value = "/api/menu/userMenu",method = RequestMethod.GET)
    public List<ParentMenuBo> getUserMenu(){
        return menu.getUserMenu();
    }

    @RequiresPermissions("menu:*")
    @RequestMapping(value = "/api/menu/menu",method = RequestMethod.POST)
    public String setMenu(MenuEntry menuEntry){
        return menu.setMenu(menuEntry);
    }

    @RequiresPermissions("menu:*")
    @RequestMapping(value = "/api/menu/menu",method = RequestMethod.GET)
    public List<MenuBo> getMenu(){
        return menu.getMenu();
    }
}
