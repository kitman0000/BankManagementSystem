package com.imbus.bank.common;

import com.imbus.bank.common.bo.BookBo;
import com.imbus.bank.common.dao.BookDao;
import com.imbus.bank.trunkModule.dao.TrunkCashDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zhong on 2020-8-22.
 * 账单业务Common类
 */
@Component
public class BookCommon {
    @Autowired
    private AgencyCommon agencyCommon;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private TrunkCashDao trunkCashDao;

    /**
     * 添加一笔现金账单
     * @param amount 金额
     * @param target 目标方
     * @param remark 备注
     * @param useTrunk 是否使用尾箱
     * @param cashObject 转账对象，1为机构 2为尾箱
     */
    public void addCashBill(BigDecimal amount,String target,String remark,boolean useTrunk,int cashObject){
        int agencyID = agencyCommon.getUserAgency();
        int tellerID = UserCommon.getUserBo().getUserID();

        int trunkID = 0;
        if(useTrunk){
            trunkID = trunkCashDao.getTellerTrunkID(UserCommon.getUserBo().getUserID());
        }

        bookDao.addCashBill(amount,target,remark,agencyID,tellerID,trunkID,cashObject);
    }

    /***
     *
     * @param amount 金额
     * @param target 目标房
     * @param remark 备注
     * @param tag 1：资产端 2：借贷端
     */
    public void addFundBill(BigDecimal amount,String target,String remark,int tag){
        int agencyID = agencyCommon.getUserAgency();
        int tellerID = UserCommon.getUserBo().getUserID();

        bookDao.addFundBill(amount,target,remark,agencyID,tellerID,tag);
    }

    /***
     *
     * @param amount 金额
     * @param target 目标房
     * @param remark 备注
     * @param tag 1：资产端 2：借贷端
     */
    public void addSystemFundBill(BigDecimal amount,String target,String remark,int tag){
        bookDao.addFundBill(amount,target,remark,0,0,tag);
    }

    public List<BookBo> getFundBook(int tag, Date startTime, Date endTime){
        return bookDao.getFundBookList(tag,startTime,endTime);
    }

    public List<BookBo> getCashBook(Date startTime,Date endTime){
        return bookDao.getCashBookList(startTime,endTime);
    }


}
