package com.imbus.bank.roleModule.service.impl;

import com.imbus.bank.roleModule.bo.PermissionBo;
import com.imbus.bank.roleModule.dao.PermissionDao;
import com.imbus.bank.roleModule.service.IPermission;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2019-12-15.
 */
@Service
public class PermissionImpl implements IPermission{
    @Autowired
    private PermissionDao permissionDao;

    /**
     * 获取所有权限
     * @return 权限ID,权限名,权限描述
     */
    @Override
    public List<PermissionBo> getPermission() {
        return permissionDao.getAllPermission();
    }
}
