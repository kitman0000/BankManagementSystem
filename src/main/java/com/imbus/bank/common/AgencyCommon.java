package com.imbus.bank.common;

import com.imbus.bank.agencyModule.dao.AgencyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhong on 2020-8-13.
 */
@Component
public class AgencyCommon {
    @Autowired
    private AgencyDao agencyDao;

    public int getUserAgency(){
        int userID = UserCommon.getUserBo().getUserID();
        return agencyDao.getUserAgencyID(userID);
    }
}
