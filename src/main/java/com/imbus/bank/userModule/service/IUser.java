package com.imbus.bank.userModule.service;

import com.imbus.bank.userModule.bo.UserBo;
import com.imbus.bank.userModule.bo.UserDetailBo;
import com.imbus.bank.userModule.entity.UserDetailEntity;

import java.util.List;

/**
 * Created by zhong on 2019-12-3.
 */
public interface IUser {
    int getUserPageNumber(String username,int status);

    List<UserBo> getUser(String username, int status, int page);

    UserDetailBo getUserDetail(int userID);

    String editUserDetail(UserDetailEntity userDetail);

    String addUserDetail(UserDetailEntity userDetail);

    String deleteUser(int userID);
}
