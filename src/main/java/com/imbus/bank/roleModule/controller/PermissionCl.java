package com.imbus.bank.roleModule.controller;

import com.imbus.bank.roleModule.bo.PermissionBo;
import com.imbus.bank.roleModule.service.impl.PermissionImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2019-12-15.
 */
@RestController
public class PermissionCl {
    @Autowired
    PermissionImpl permission;

    @RequiresPermissions("role:*")
    @RequestMapping(value = "/api/role/permission",method = RequestMethod.GET)
    public List<PermissionBo> getPermission(){
        return permission.getPermission();
    }
}
