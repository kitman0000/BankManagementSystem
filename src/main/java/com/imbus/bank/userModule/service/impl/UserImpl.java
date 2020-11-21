package com.imbus.bank.userModule.service.impl;

import com.imbus.bank.common.UserCommon;
import com.imbus.bank.userModule.bo.UserBo;
import com.imbus.bank.userModule.bo.UserDetailBo;
import com.imbus.bank.userModule.dao.UserDao;
import com.imbus.bank.userModule.entity.UserDetailEntity;
import com.imbus.bank.userModule.service.IUser;
import com.imbus.bank.userModule.type.UserResult;
import com.imbus.bank.utils.PageDivideUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2019-12-3.
 */
@Service
public class UserImpl implements IUser{

    @Autowired
    private UserDao userDao;

    private final int ROWS_ONE_PAGE = 10;

    /**
     * 获取页面数量
     * @param username 用户名，模糊查询
     * @param status 账号状态
     * @return
     */
    @Override
    public int getUserPageNumber(String username, int status) {
        int userNumber = userDao.getUserNumber(username,status);

        return PageDivideUtil.getCountOfPages(userNumber,ROWS_ONE_PAGE);
    }

    /**
     * 获取某页用户
     * @param username 用户名，模糊查询
     * @param status 账号状态
     * @param page 页数
     * @return
     */
    @Override
    public List<UserBo> getUser(String username, int status, int page) {
        int startRow = (page -1) * ROWS_ONE_PAGE;
        return userDao.getUser(username,status,startRow,ROWS_ONE_PAGE);
    }

    /**
     * 获取某用户详细信息
     * @param userID 用户ID
     * @return 详细信息
     */
    @Override
    public UserDetailBo getUserDetail(int userID) {
        return userDao.getUserDetail(userID);
    }

    /**
     * 编辑某用户信息
     * @param userDetail 用户详情
     * @return 是否成功
     */
    @Override
    public String editUserDetail(UserDetailEntity userDetail) {
        if(!userDetail.getPwd().equals("")){
            userDetail.setPwd(UserCommon.encodePwd(userDetail.getPwd()));
        }

        userDao.updateUser(userDetail);
        return UserResult.USER_EDIT_SUCCESS.toString();
    }

    /**
     * 添加一个用户
     * @param userDetail 用户详情
     * @return 是否成功
     */
    @Override
    public String addUserDetail(UserDetailEntity userDetail) {
        // 对密码二次加密
        userDetail.setPwd(UserCommon.encodePwd(userDetail.getPwd()));

        userDao.insertUser(userDetail);
        return UserResult.USER_ADD_SUCCESS.toString();
    }

    /**
     * 删除一个用户
     * @param userID 用户ID
     * @return 是否成功
     */
    @Override
    @RequiresPermissions("user:*")
    public String deleteUser(int userID) {
        userDao.deleteUser(userID);
        return UserResult.USER_DELETE_SUCCESS.toString();
    }

    /**
     * 获取用户头像
     * @param userID 用户ID
     * @return 头像地址
     */
    @Override
    @RequiresPermissions("user:*")
    public String getUserPicture() {
        int userID = UserCommon.getUserBo().getUserID();
        return userDao.getUserPicture(userID);
    }
}
