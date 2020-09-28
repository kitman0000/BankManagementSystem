package com.imbus.bank.userModule.service;

/**
 * Created by zhong on 2019-12-3.
 */
public interface IAccount {
    String login(String username,String pwd);

    String logout(String token);

    String changePwd(String oldPwd, String newPwd);
}
