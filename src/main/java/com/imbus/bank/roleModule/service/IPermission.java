package com.imbus.bank.roleModule.service;

import com.imbus.bank.roleModule.bo.PermissionBo;

import java.util.List;

/**
 * Created by zhong on 2019-12-15.
 */
public interface IPermission {
    List<PermissionBo> getPermission();
}
