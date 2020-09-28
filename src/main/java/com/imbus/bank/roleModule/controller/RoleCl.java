package com.imbus.bank.roleModule.controller;

import com.imbus.bank.roleModule.bo.RoleBo;
import com.imbus.bank.roleModule.service.impl.RoleImpl;
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
public class RoleCl {
    @Autowired
    RoleImpl role;

    @RequiresPermissions("role:*")
    @RequestMapping(value = "/api/role/role",method = RequestMethod.GET)
    public List<RoleBo> getRole(){
        return role.getRole();
    }

    @RequiresPermissions("role:*")
    @RequestMapping(value = "/api/role/role",method = RequestMethod.POST)
    public String addRole(String roleName){
        return role.addRole(roleName);
    }

    @RequiresPermissions("role:*")
    @RequestMapping(value = "/api/role/role",method = RequestMethod.DELETE)
    public String deleteRole(int roleID){
        return role.deleteRole(roleID);
    }

    @RequiresPermissions("role:*")
    @RequestMapping(value = "/api/role/rolePermission",method = RequestMethod.POST)
    public String addRolePermission(int roleID,int permissionID){
        return role.addRolePermission(roleID,permissionID);
    }

    @RequiresPermissions("role:*")
    @RequestMapping(value = "/api/role/rolePermission",method = RequestMethod.DELETE)
    public String deleteRolePermission(int roleID,int permissionID){
        return role.deleteRolePermission(roleID,permissionID);
    }
}
