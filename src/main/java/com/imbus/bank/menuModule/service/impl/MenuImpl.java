package com.imbus.bank.menuModule.service.impl;

import com.imbus.bank.menuModule.bo.MenuBo;
import com.imbus.bank.menuModule.bo.ParentMenuBo;
import com.imbus.bank.menuModule.dao.MenuDao;
import com.imbus.bank.menuModule.entity.MenuEntry;
import com.imbus.bank.menuModule.service.IMenu;
import com.imbus.bank.menuModule.type.MenuResult;
import com.imbus.bank.roleModule.bo.PermissionBo;
import com.imbus.bank.roleModule.bo.RoleBo;
import com.imbus.bank.roleModule.dao.PermissionDao;
import com.imbus.bank.roleModule.dao.RoleDao;
import com.imbus.bank.userModule.dao.AccountDao;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhong on 2019-12-16.
 */
@Service
public class MenuImpl implements IMenu{
    @Autowired
    MenuDao menuDao;

    @Autowired
    AccountDao accountDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    PermissionDao permissionDao;

    /**
     * 设置菜单
     * @param menu 菜单对象
     * @return 是否成功
     */
    @Override
    public String setMenu(MenuEntry menu) {
        menuDao.updatePermissionMenu(menu);
        return MenuResult.MENU_SET_SUCCESS.toString();
    }

    /**
     * 获取所有菜单
     * @return 所有菜单
     */
    @Override
    public List<MenuBo> getMenu() {
        return menuDao.getMenuSetting();
    }

    /**
     * 获取用户菜单
     * @return 用户菜单对象
     */
    @Override
    public List<ParentMenuBo> getUserMenu() {

        // 获取登录用户名
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        // 获取用户id
        int userID = accountDao.findUserIDByName(username).getUserID();
        // 获取用户角色
        RoleBo roleBo = roleDao.getUserRole(userID);

        List<Integer> permissionIDList = new LinkedList<Integer>();
        for (PermissionBo permissionBo : permissionDao.getRolePermission(roleBo.getRoleID())) {
            // 添加权限
            int permissionID = permissionBo.getPermissionID();
            permissionIDList.add(permissionID);
        }

        // 获取所有父菜单
        List<ParentMenuBo> parentMenuBoList = menuDao.getAllParentMenu();

        Iterator parentMenuIterator = parentMenuBoList.iterator();
        while (parentMenuIterator.hasNext())
        {
            ParentMenuBo parentMenuBo = (ParentMenuBo) parentMenuIterator.next();

            List<MenuBo> menuBoList = menuDao.getMenuByParent(parentMenuBo.getParentMenuID());

            Iterator menuIterator = menuBoList.iterator();
            while (menuIterator.hasNext()){
                MenuBo currentMenu = (MenuBo)((menuIterator.next()));

                int permissionID = currentMenu.getPermissionID();
                if (!permissionIDList.contains(permissionID)){
                    menuIterator.remove();
                }
            }

            if(!menuBoList.isEmpty()){
                parentMenuBo.setMenuBoList(menuBoList);
            }else {
                parentMenuIterator.remove();
            }
        }

        return parentMenuBoList;
    }
}
