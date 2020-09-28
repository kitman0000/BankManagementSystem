package com.imbus.bank;

import com.imbus.bank.roleModule.service.impl.PermissionImpl;
import com.imbus.bank.roleModule.service.impl.RoleImpl;
import com.imbus.bank.userModule.entity.UserDetailEntity;
import com.imbus.bank.userModule.service.impl.AccountImpl;
import com.imbus.bank.userModule.service.impl.UserImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zhong on 2019-12-14.
 */
@RunWith(SpringRunner.class)
@Component
@SpringBootTest
public class RoleTest {
    @Autowired
    AccountImpl account;

    @Autowired
    UserImpl user;

    @Autowired
    RoleImpl role;

    @Autowired
    PermissionImpl permission;

    @Test
    public void roleTest() {
        Logger logger = Logger.getLogger("UNIT TEST");
        account.login("hihihi","abc");
        //accountID.login("ah","agc");

        UserDetailEntity userDetailEntity = new UserDetailEntity();
        userDetailEntity.setUsername("aaa");
        userDetailEntity.setNickName("bbb");
        userDetailEntity.setPwd("12345");
        String result = user.addUserDetail(userDetailEntity);

        logger.log(Level.INFO,result);
    }

    @Test
    public void getAllRoles(){
        Logger logger = Logger.getLogger("UNIT TEST");
        account.login("hjh","c");
        logger.log(Level.INFO,role.getRole().toString());
    }

    public void addRole(){
        Logger logger = Logger.getLogger("UNIT TEST");
        logger.log(Level.INFO,role.addRole("Clerk"));
    }

    public void deleteRole(){
        Logger logger = Logger.getLogger("UNIT TEST");
        logger.log(Level.INFO,role.deleteRole(3));
    }

    public void addRolePermission(){
        Logger logger = Logger.getLogger("UNIT TEST");
        logger.log(Level.INFO,role.addRolePermission(2,1));
    }

    public void deleteRolePermission(){
        Logger logger = Logger.getLogger("UNIT TEST");
        logger.log(Level.INFO,role.deleteRolePermission(2,1));
    }

    @Test
    public void getAllPermission(){
        Logger logger = Logger.getLogger("UNIT TEST");
        logger.log(Level.INFO,permission.getPermission().toString());
    }

}
