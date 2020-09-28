package com.imbus.bank.publicServiceModule.dao;

import com.imbus.bank.publicServiceModule.bo.PublicAccountApplyDetailBo;
import com.imbus.bank.publicServiceModule.bo.PublicAccountBo;
import com.imbus.bank.publicServiceModule.entity.PublicAccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-8-12.
 */
@Mapper
@Repository
public interface PublicAccountDao {
    void createAccountApply(@Param("publicAccountEntity") PublicAccountEntity publicAccountEntity);

    int getBasicAccountNumber(int clientID);

    void createAccount(@Param("publicAccountEntity") PublicAccountApplyDetailBo PublicAccountEntity);

    void cancelAccount(String accountID);

    int checkAccountPwd(@Param("accountID") String accountID, @Param("pwd") String pwd);

    BigDecimal getAccountBalance(String accountID);

    void addAccountBalance(@Param("accountID") String accountID, @Param("amount") BigDecimal amount);

    void removeAccountBalance(@Param("accountID") String accountID, @Param("amount") BigDecimal amount);

    List<PublicAccountBo> getAccountBalanceList();

    List<PublicAccountBo> getClientAccount(int clientID);
}
