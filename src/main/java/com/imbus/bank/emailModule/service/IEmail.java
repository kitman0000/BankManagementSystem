package com.imbus.bank.emailModule.service;

import com.imbus.bank.emailModule.bo.EmailBo;
import com.imbus.bank.emailModule.bo.EmailDetailBo;
import com.imbus.bank.emailModule.entity.EmailSearchEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by zhong on 2020-4-13.
 */
public interface IEmail {
    String sendMail(int receiverID, String title, String content, boolean isImportant, String filenameJson);

    int getMailNumber(EmailSearchEntity emailSearchEntity);

    List<EmailBo> getReceiveEmailList(EmailSearchEntity emailSearchEntity);

    EmailDetailBo getEmailDetail(int emailID);

    int getSentMailPageNumber(EmailSearchEntity emailSearchEntity);

    List<EmailBo> getSentEmailList(EmailSearchEntity emailSearchEntity);

    EmailDetailBo getSentEmailDetail(int emailID);
}
