package com.imbus.bank;


import com.imbus.bank.userModule.service.IAccount;
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

@RunWith(SpringRunner.class)
@Component
@SpringBootTest
public class BankApplicationTests {

    @Autowired
    AccountImpl account;

    @Autowired
    UserImpl user;

    @Test
    public void contextLoads() {
        Logger logger = Logger.getLogger("UNIT TEST");
        account.login("a","a");
        String result = user.addUserDetail(null);
        logger.log(Level.INFO,result);
    }

}
