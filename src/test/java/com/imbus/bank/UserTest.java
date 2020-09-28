package com.imbus.bank;

import com.imbus.bank.userModule.bo.UserBo;
import com.imbus.bank.userModule.entity.UserDetailEntity;
import com.imbus.bank.userModule.service.impl.UserImpl;
import com.imbus.bank.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zhong on 2019-12-6.
 */

@RunWith(SpringRunner.class)
@Component
@SpringBootTest
public class UserTest {

    @Autowired
    UserImpl user;

    Logger logger = Logger.getLogger("TEST LOGGER");

    public void getUserPageNumberTest()
    {
        int result = user.getUserPageNumber("a",-1);
        logger.log(Level.INFO,String.valueOf(result));
    }

    @Test
    public void getUser()
    {
        List<UserBo> userBo = user.getUser("123",0,1);
        logger.log(Level.INFO, JsonUtil.objectToJson(userBo));
    }

    @Test
    public void editUser()
    {
        UserDetailEntity userDetailEntity = new UserDetailEntity();
        userDetailEntity.setUserID(1);
        userDetailEntity.setUsername("hihihi");
        userDetailEntity.setNickName("jiajia");
        user.editUserDetail(userDetailEntity);
    }

    @Test
    public void addUser()
    {
        UserDetailEntity userDetailEntity = new UserDetailEntity();
        userDetailEntity.setUsername("蒋中正");
        userDetailEntity.setNickName("蒋介石");
        userDetailEntity.setPwd("12345");
        user.addUserDetail(userDetailEntity);
    }
}
