package com.imbus.bank.personalServiceModule.dao;


import com.imbus.bank.personalServiceModule.bo.PersonalAccountBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.imbus.bank.personalServiceModule.entity.PersonalAccountEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhong on 2020-8-15.
 */
@Mapper
@Repository
public interface PersonalAccountDao {
    int getClassOneCardNumber(int clientID);

    int getClassTwoCardNumber(int clientID);

    void createAccount(@Param("personalAccountEntity") PersonalAccountEntity personalAccountEntity);

    void cancelAccount(String accountID);

    int checkAccountPwd(@Param("accountID") String accountID, @Param("pwd") String pwd);

    BigDecimal getAccountBalance(String accountID);

    void addAccountBalance(@Param("accountID") String accountID, @Param("amount") BigDecimal amount);

    void removeAccountBalance(@Param("accountID") String accountID, @Param("amount") BigDecimal amount);

    List<PersonalAccountBo> getAccountBalanceList();

    List<PersonalAccountBo> getClientAccount(int clientID);
}
