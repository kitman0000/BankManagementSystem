package com.imbus.bank.userModule.dao;

import com.imbus.bank.userModule.bo.UserBo;
import com.imbus.bank.userModule.bo.UserDetailBo;
import com.imbus.bank.userModule.entity.UserDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhong on 2019-12-6.
 */
@Mapper
@Repository
public interface UserDao {

    // 获取用户数量
    int getUserNumber(@Param("username") String username, @Param("status") int status);

    // 获取某页用户信息
    List<UserBo> getUser(@Param("username") String username,  @Param("status") int status,
                         @Param("startRow") int startRow, @Param("rowsOnePage") int rowsOnePage);

    // 获取单个用户详细信息
    UserDetailBo getUserDetail(int userID);

    // 编辑单个用户详细信息
    void updateUser(@Param("userDetail") UserDetailEntity userDetail);

    // 添加单个用户
    void insertUser(@Param("userDetail") UserDetailEntity userDetail);

    // 删除单个用户(注销账号)
    void deleteUser(int userID);
}
