package com.imbus.bank.roleModule.dao;

import com.imbus.bank.roleModule.bo.PermissionBo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhong on 2019-12-14.
 */
@Repository
@Mapper
public interface PermissionDao {
    // 获取角色权限
    List<PermissionBo> getRolePermission(int roleID);

    // 获取所有权限
    List<PermissionBo> getAllPermission();
}
