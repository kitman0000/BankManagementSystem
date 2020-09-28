package com.imbus.bank;

import com.imbus.bank.menuModule.entity.MenuEntry;
import com.imbus.bank.menuModule.service.impl.MenuImpl;
import com.imbus.bank.userModule.service.impl.AccountImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zhong on 2019-12-16.
 */
@RunWith(SpringRunner.class)
@Component
@SpringBootTest
public class MenuTest {

    @Autowired
    MenuImpl menu;

    @Autowired
    AccountImpl account;

    @Test
    public void userMenuTest(){
        Logger logger = Logger.getLogger("UNIT TEST");
        account.login("hihihi","abc");

        logger.log(Level.INFO,menu.getUserMenu().toString());
    }

    @Test
    public void setMenu(){
        Logger logger = Logger.getLogger("UNIT TEST");
        account.login("hihihi","abc");

        MenuEntry menuEntry = new MenuEntry();
        menuEntry.setPermissionID(1);
        menuEntry.setMenuHref("www.baidu.com");
        menuEntry.setMenuTitle("处决刑犯");

        logger.log(Level.INFO,menu.setMenu(menuEntry).toString());
    }

    @Test
    public void getMenuSetting()
    {
        Logger logger = Logger.getLogger("UNIT TEST");
        account.login("hihihi","abc");

        logger.log(Level.INFO,menu.getMenu().toString());
    }
}
