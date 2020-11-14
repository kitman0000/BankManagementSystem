package com.imbus.bank.config;

import com.imbus.bank.roleModule.bo.PermissionBo;
import com.imbus.bank.roleModule.bo.RoleBo;
import com.imbus.bank.roleModule.dao.RoleDao;
import com.imbus.bank.roleModule.dao.PermissionDao;

import com.imbus.bank.userModule.bo.UserBo;
import com.imbus.bank.userModule.dao.AccountDao;
import com.imbus.bank.userModule.dao.UserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现AuthorizingRealm接口用户用户认证
 * @author Louis
 * @date Jun 20, 2019
 */
public class MyShiroRealm extends AuthorizingRealm {
	@Autowired
	AccountDao accountDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	PermissionDao permissionDao;

	@Autowired
	UserDao userDao;

	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {

		if (authenticationToken.getPrincipal() == null) {
			return null;
		}
		// 获取用户信息
		String username = authenticationToken.getPrincipal().toString();
		UserBo userBo = accountDao.findUserIDByName(username);

		if(userBo == null){
			return null;
		}

		if(userBo.getStatus() != 1)
		{
			throw new DisabledAccountException();
		}

		// 这里验证authenticationToken和simpleAuthenticationInfo的信息
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,
				userBo.getPwd(), getName());

		return simpleAuthenticationInfo;

	}

	/**
	 * 角色权限和对应权限添加
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录用户名
		String username = (String) principalCollection.getPrimaryPrincipal();
		// 获取用户id
		int userID = accountDao.findUserIDByName(username).getUserID();

		// 获取用户角色
		RoleBo roleBo = roleDao.getUserRole(userID);

		// 添加角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();


		// 添加角色
		simpleAuthorizationInfo.addRole(roleBo.getRoleName());

		for (PermissionBo permissionBo : permissionDao.getRolePermission(roleBo.getRoleID())) {
			// 添加权限
			simpleAuthorizationInfo.addStringPermission(permissionBo.getPermissionName());
		}
		return simpleAuthorizationInfo;
	}

	public void clearCachedAuthorization(){
		//清空权限缓存
		clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}

}