package com.imbus.bank.emailModule.dao;

import com.imbus.bank.emailModule.bo.EmailBo;
import com.imbus.bank.emailModule.bo.EmailDetailBo;
import com.imbus.bank.emailModule.entity.EmailSearchEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhong on 2020-4-13.
 */

@Mapper
@Repository
public interface EmailDao {
    void insertEmail(@Param("senderID") int senderID, @Param("receiverID") int receiverID,@Param("title") String title, @Param("content") String content, @Param("isImportant") boolean isImportant, @Param("attachment") String attachment);

    int getMailNumber(@Param("userID") int userID, @Param("emailSearchEntity") EmailSearchEntity emailSearchEntity);

    List<EmailBo> getMailList(@Param("userID") int userID, @Param("emailSearchEntity") EmailSearchEntity emailSearchEntity, @Param("startRow") int startRow, @Param("rowsOnePage") int rowsOnePage);

    EmailDetailBo getMailDetail(@Param("mailID") int mailID, @Param("userID") int userID);

    void setMailHasRead(int mailID);

    int getSentMailNumber(@Param("userID") int userID, @Param("emailSearchEntity") EmailSearchEntity emailSearchEntity);

    List<EmailBo> getSentMailList(@Param("userID") int userID, @Param("emailSearchEntity") EmailSearchEntity emailSearchEntity, @Param("startRow") int startRow, @Param("rowsOnePage") int rowsOnePage);

    EmailDetailBo getSentMailDetail(@Param("mailID") int mailID, @Param("userID") int userID);
}
