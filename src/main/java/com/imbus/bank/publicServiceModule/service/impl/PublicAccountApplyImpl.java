package com.imbus.bank.publicServiceModule.service.impl;

import com.imbus.bank.common.AgencyCommon;
import com.imbus.bank.common.BankAccountCommon;
import com.imbus.bank.common.DateUtil;
import com.imbus.bank.common.UserCommon;
import com.imbus.bank.publicServiceModule.bo.PublicAccountApplyBo;
import com.imbus.bank.publicServiceModule.bo.PublicAccountApplyDetailBo;
import com.imbus.bank.publicServiceModule.dao.PublicAccountApplyDao;
import com.imbus.bank.publicServiceModule.dao.PublicAccountDao;
import com.imbus.bank.publicServiceModule.service.IPublicAccountApply;
import com.imbus.bank.publicServiceModule.type.HandlePublicAccountApplyResult;
import com.imbus.bank.utils.PageDivideUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhong on 2020-8-13.
 */
@Service
public class PublicAccountApplyImpl implements IPublicAccountApply {

    @Autowired
    private AgencyCommon agencyCommon;

    @Autowired
    private PublicAccountDao publicAccountDao;

    @Autowired
    private BankAccountCommon bankAccountCommon;

    final private int ROWS_ON_PAGE = 10;

    @Autowired
    private PublicAccountApplyDao publicAccountApplyDao;

    @Override
    public int getApplyPage() {
        int agencyID = agencyCommon.getUserAgency();
        int count = publicAccountApplyDao.getApplyCount(agencyID);
        return PageDivideUtil.getCountOfPages(count,ROWS_ON_PAGE);
    }

    @Override
    public List<PublicAccountApplyBo> getApply(int page) {
        int agencyID = agencyCommon.getUserAgency();

        int startRow = (page -1) * ROWS_ON_PAGE;
        return publicAccountApplyDao.getApply(agencyID,startRow,ROWS_ON_PAGE);
    }

    @Override
    public HandlePublicAccountApplyResult handleAccountApply(int id, int result) {
        PublicAccountApplyDetailBo applyDetailBo = publicAccountApplyDao.getApplyDetail(id);
        // 请求已被处理过
        if(applyDetailBo.getHandleStatus() != 0){
            return HandlePublicAccountApplyResult.HANDLE_FAILED;
        }

        // 开户核准号暂定为yyyyMMddHHmmssSSS+id

        String authNo = "";
        if(result == 1){
            authNo = DateUtil.formatLongyyyyMMddHHmmssSSSFormatByDate() + id;
            applyDetailBo.setAuthNo(authNo);
        }

        int userID = UserCommon.getUserBo().getUserID();

        // 更新处理结果
        publicAccountApplyDao.updateApplyStatus(id,result,userID,authNo);

        if(result == 1){
            applyDetailBo.setAuthDate(new Date());

            // 对应不同的账户类型，使用不同的bin码
            String binCode = "";
            switch (applyDetailBo.getAccountType()){
                case "1":binCode = BankAccountCommon.BIN_CODE_PUBLIC_1;break;
                case "2":binCode = BankAccountCommon.BIN_CODE_PUBLIC_2;break;
                case "3":binCode = BankAccountCommon.BIN_CODE_PUBLIC_3;break;
                case "4":binCode = BankAccountCommon.BIN_CODE_PUBLIC_4;break;
            }

            String bankAccount = bankAccountCommon.getNewBankAccountNumber(binCode);
            applyDetailBo.setBankAccountID(bankAccount);

            publicAccountDao.createAccount(applyDetailBo);
        }

        return HandlePublicAccountApplyResult.HANDLE_SUCCESS;
    }
}
