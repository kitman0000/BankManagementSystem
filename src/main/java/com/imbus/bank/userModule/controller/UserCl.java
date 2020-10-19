package com.imbus.bank.userModule.controller;

import com.imbus.bank.userModule.bo.UserBo;
import com.imbus.bank.userModule.bo.UserDetailBo;
import com.imbus.bank.userModule.entity.UserDetailEntity;
import com.imbus.bank.userModule.service.IUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhong on 2019-12-10.
 */

@RestController
public class UserCl {
    @Autowired
    IUser user;

    @RequiresPermissions("user:*")
    @RequestMapping(value = "/api/user/userPageNumber",method = RequestMethod.GET)
    public int getUserPageNumber(String username,int status){
        return user.getUserPageNumber(username,status);
    }

    @RequestMapping(value = "/api/user/userPicture",method = RequestMethod.GET)
    public String getUserPicture(){return  user.getUserPicture();}

    @RequiresPermissions("user:*")
    @RequestMapping(value = "/api/user/user",method = RequestMethod.GET)
    public List<UserBo> getUser(String username,int status,int page){
        return user.getUser(username,status,page);
    }

    @RequiresPermissions("user:*")
    @RequestMapping(value = "/api/user/userDetail",method = RequestMethod.GET)
    public UserDetailBo getUserDetail(int userID){
        return user.getUserDetail(userID);
    }

    @RequiresPermissions("user:*")
    @RequestMapping(value = "/api/user/userDetail",method = RequestMethod.PUT)
    public String editUserDetail(UserDetailEntity userDetailEntity){
        return user.editUserDetail(userDetailEntity);
    }

    @RequiresPermissions("user:*")
    @RequestMapping(value = "/api/user/userDetail",method = RequestMethod.POST)
    public String addUserDetail(UserDetailEntity userDetailEntity){
        return user.addUserDetail(userDetailEntity);
    }

    @RequiresPermissions("user:*")
    @RequestMapping(value = "/api/user/user",method = RequestMethod.DELETE)
    public String deleteUser(int userID){
        return user.deleteUser(userID);
    }
}
