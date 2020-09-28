package com.imbus.bank.menuModule.dao;

import com.imbus.bank.menuModule.bo.MenuBo;
import com.imbus.bank.menuModule.bo.ParentMenuBo;
import com.imbus.bank.menuModule.entity.MenuEntry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhong on 2019-12-16.
 */
@Mapper
@Repository
public interface MenuDao {

    // 设置菜单权限
    void updatePermissionMenu(@Param("menu") MenuEntry menu);

    // 获取当前菜单设置
    List<MenuBo> getMenuSetting();

    // 获取某权限的菜单
    MenuBo getPermissionMenu(int permissionID);

    // 获取所有父菜单
    List<ParentMenuBo> getAllParentMenu();

    // 获取某父菜单的所有菜单
    List<MenuBo> getMenuByParent(int parentMenuID);
}
