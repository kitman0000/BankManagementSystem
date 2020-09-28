package com.imbus.bank.roleModule.service.impl;

import com.imbus.bank.roleModule.bo.PermissionBo;
import com.imbus.bank.roleModule.bo.RoleBo;
import com.imbus.bank.roleModule.dao.PermissionDao;
import com.imbus.bank.roleModule.dao.RoleDao;
import com.imbus.bank.roleModule.service.IRole;
import com.imbus.bank.roleModule.type.RoleResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2019-12-15.
 */
@Service
public class RoleImpl implements IRole{

    @Autowired
    RoleDao roleDao;

    @Autowired
    PermissionDao permissionDao;

    /**
     * 获取所有角色
     * @return 角色ID，角色名称
     */
    @Override
    public List<RoleBo> getRole() {
        List<RoleBo> roleBoList = roleDao.getAllUser();
        for(RoleBo roleBo:roleBoList){
            List<PermissionBo> permissionBoList = permissionDao.getRolePermission(roleBo.getRoleID());
            roleBo.setPermissionBoList(permissionBoList);
        }

        return roleBoList;
    }

    /**
     * 添加新角色
     * @param roleName 角色名
     * @return 是否成功
     */
    @Override
    public String addRole(String roleName) {
        roleDao.addRole(roleName);
        return RoleResult.ROLE_ADD_SUCCESS.toString();
    }

    /**
     * 删除角色
     * @param roleID 角色ID
     * @return 是否成功
     */
    @Override
    public String deleteRole(int roleID) {
        roleDao.deleteRole(roleID);
        return RoleResult.ROLE_DELETE_SUCCESS.toString();
    }

    /**
     * 为角色添加权限
     * @param roleID 角色ID
     * @param permissionID 权限ID
     * @return 是否成功，重复
     */
    @Override
    public String addRolePermission(int roleID, int permissionID) {
        int intResult = roleDao.checkRolePermission(roleID,permissionID);
        if(intResult != 0) // 该角色已拥有该权限
            return RoleResult.PERMISSION_HAS_EXIST.toString();

        roleDao.addRolePermission(roleID,permissionID);
        return RoleResult.PERMISSION_ADD_SUCCESS.toString();
    }

    /**
     * 为角色删除权限
     * @param roleID 角色ID
     * @param permission 角色
     * @return 是否成功
     */
    @Override
    public String deleteRolePermission(int roleID, int permission) {
        roleDao.deleteRolePermission(roleID,permission);
        return RoleResult.PERMISSION_DELETE_SUCCESS.toString();
    }

}
