package com.imbus.bank.common;

import com.imbus.bank.agencyModule.dao.AgencyDao;
import com.imbus.bank.common.dao.BankAccountDao;
import com.imbus.bank.utils.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhong on 2020-8-14.
 */
@Component
public class BankAccountCommon {
    @Autowired
    AgencyCommon agencyCommon;

    @Autowired
    AgencyDao agencyDao;

    @Autowired
    BankAccountDao bankAccountDao;

    public enum AccountType{
        PUBLIC,PRIVATE,OTHER_BANK
    }

    // 密钥
    private static final String SECRET_KEY = "FJX361!";

    public static final String BIN_CODE_PRIVATE_1 = "611010";

    public static final String BIN_CODE_PRIVATE_2 = "611011";

    public static final String BIN_CODE_PUBLIC_1 = "611012";

    public static final String BIN_CODE_PUBLIC_2 = "611013";

    public static final String BIN_CODE_PUBLIC_3 = "611014";

    public static final String BIN_CODE_PUBLIC_4 = "611015";

    /***
     * 生成银行卡号
     * @param binCode bin码
     * @return
     */
    public String getNewBankAccountNumber(String binCode){
        int agencyID = agencyCommon.getUserAgency();
        String areaCode = agencyDao.getAgencyCityCode(agencyID);
        String bankCode = agencyDao.getBankCode(agencyID);

        String lastAutoNumber = bankAccountDao.getLastAutoNumber(binCode,areaCode,bankCode);

        String autoNumber;
        if(lastAutoNumber != null) {
            autoNumber = String.valueOf(Integer.parseInt(lastAutoNumber) + 1);

            // 当位数不够6位时，给数字前加0
            int length = autoNumber.length();
            for(int i = length;i<6;i++){
                autoNumber = "0" + autoNumber;
            }

        }else {
            autoNumber = "000000";
        }

        bankAccountDao.insertAutoNumber(binCode,areaCode,bankCode,autoNumber);

        return binCode + areaCode + bankCode + autoNumber;
    }

    /***
     * 检查账户类型
     * @param account 账号
     * @return 账户类型
     */
    public AccountType getAccountType(String account){

        // 个人账户
        if(account.startsWith(BIN_CODE_PRIVATE_1) || account.startsWith(BIN_CODE_PRIVATE_2)){
            return AccountType.PRIVATE;
        }else if(
               account.startsWith(BIN_CODE_PUBLIC_1)
            || account.startsWith(BIN_CODE_PUBLIC_2)
            || account.startsWith(BIN_CODE_PUBLIC_3)
            || account.startsWith(BIN_CODE_PUBLIC_4)){
            // 对公账户
            return AccountType.PUBLIC;
        }else {
            // 其他银行
            return AccountType.OTHER_BANK;
        }

    }



    public static String encodePwd(String pwd){
        return EncodeUtil.encodeHmac256(pwd,SECRET_KEY);
    }
}
