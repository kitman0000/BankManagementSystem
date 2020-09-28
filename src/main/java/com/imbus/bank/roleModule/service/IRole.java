package com.imbus.bank.roleModule.service;

import com.imbus.bank.roleModule.bo.PermissionBo;
import com.imbus.bank.roleModule.bo.RoleBo;

import java.util.List;

/**
 * Created by zhong on 2019-12-15.
 */
public interface IRole {
    List<RoleBo> getRole();

    String addRole(String roleName);

    String deleteRole(int roleID);

    String addRolePermission(int roleID,int permission);

    String deleteRolePermission(int roleID,int permission);

}
