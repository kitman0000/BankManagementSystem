package com.imbus.bank.emailModule.controller;

import com.imbus.bank.emailModule.bo.EmailBo;
import com.imbus.bank.emailModule.bo.EmailDetailBo;
import com.imbus.bank.emailModule.entity.EmailSearchEntity;
import com.imbus.bank.emailModule.service.IEmail;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by zhong on 2020-4-13.
 */

@RestController
public class EmailCl {
    @Autowired
    IEmail email;

    @RequiresPermissions("mail:send")
    @RequestMapping(value = "/api/mail/mail",method = RequestMethod.POST)
    public String sendMail(int receiverID,String title, String content, boolean isImportant, String file){
        return email.sendMail(receiverID, title, content, isImportant, file);
    }

    @RequiresPermissions("mail:receive")
    @RequestMapping(value = "/api/mail/mailPage",method = RequestMethod.GET)
    public int getMailPageNumber(EmailSearchEntity emailSearchEntity){
        return email.getMailNumber(emailSearchEntity);
    }

    @RequiresPermissions("mail:receive")
    @RequestMapping(value = "/api/mail/mailList",method = RequestMethod.GET)
    public List<EmailBo> getReceiveEmailList(EmailSearchEntity emailSearchEntity){
        return email.getReceiveEmailList(emailSearchEntity);
    }

    @Transactional
    @RequiresPermissions("mail:receive")
    @RequestMapping(value = "/api/mail/mailDetail",method = RequestMethod.GET)
    public EmailDetailBo getEmailDetail(int mailID){
        return email.getEmailDetail(mailID);
    }

    @RequiresPermissions("mail:send")
    @RequestMapping(value = "/api/mail/sentMailPage",method = RequestMethod.GET)
    public int getSentMailPageNumber(EmailSearchEntity emailSearchEntity){
        return email.getSentMailPageNumber(emailSearchEntity);
    }

    @RequiresPermissions("mail:send")
    @RequestMapping(value = "/api/mail/mailSentList",method = RequestMethod.GET)
    public List<EmailBo> getSentEmailList(EmailSearchEntity emailSearchEntity){
        return email.getSentEmailList(emailSearchEntity);
    }

    @RequiresPermissions("mail:send")
    @RequestMapping(value = "/api/mail/mailSentDetail",method = RequestMethod.GET)
    public EmailDetailBo getEmailSentDetail(int mailID){
        return email.getSentEmailDetail(mailID);
    }
}
