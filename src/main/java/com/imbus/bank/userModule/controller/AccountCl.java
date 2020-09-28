package com.imbus.bank.userModule.controller;

import com.imbus.bank.userModule.service.IAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2019-12-3.
 */

@RestController
public class AccountCl {
    @Autowired
    IAccount account;

    /**
     * 用户登录
     * @param username 用户名
     * @param pwd 密码
     * @return token（成功），USER_LOGIN_FAILED（错误）
     */
    @RequestMapping(value = "/api/account/login",method = RequestMethod.POST)
    public String login(String username,String pwd)
    {
        return account.login(username,pwd);
    }

    /**
     * 用户修改密码
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 新token/失败
     */
    @RequestMapping(value = "/api/account/changePwd",method = RequestMethod.POST)
    public String changePwd(String oldPwd,String newPwd){
        return account.changePwd(oldPwd,newPwd);
    }


    /**
     * 用户登出
     * @param token token
     * @return 是否成功
     */
    @RequestMapping(value = "/api/account/logout",method = RequestMethod.POST)
    public String logout(String token) {
        return account.logout(token);
    }

}
