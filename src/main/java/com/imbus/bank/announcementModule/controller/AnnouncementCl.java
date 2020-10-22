package com.imbus.bank.announcementModule.controller;


import com.imbus.bank.announcementModule.bo.AnnouncementBo;
import com.imbus.bank.announcementModule.bo.AnnouncementDetailBo;
import com.imbus.bank.announcementModule.entity.AnnouncementEntity;
import com.imbus.bank.announcementModule.service.IAnnouncement;
import com.imbus.bank.announcementModule.type.AnnouncementType;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "公告接口")
@RestController
public class AnnouncementCl {
    @Autowired
    IAnnouncement announcement;

    @RequestMapping(value = "/api/announcement/announcementPageNumber",method = RequestMethod.GET)
    public int getAnnouncementPageNumber(AnnouncementEntity announcementEntity){
        return announcement.getAnnouncementPageNumber(announcementEntity);
    }

    @RequestMapping(value = "/api/announcement/announcement",method = RequestMethod.POST)
    public AnnouncementType addAnnouncement(AnnouncementEntity announcementEntity){
        return announcement.addAnnouncement(announcementEntity);
    }

    @RequestMapping(value = "/api/announcement/announcementDetail",method = RequestMethod.GET)
    public AnnouncementDetailBo getAnnouncementDetail(int announcementID){
        return announcement.getAnnouncementDetail(announcementID);
    }

    @RequestMapping(value = "/api/announcement/announcement",method = RequestMethod.PUT)
    public AnnouncementType editAnnouncement(AnnouncementEntity announcementEntity){
        return announcement.editAnnouncement(announcementEntity);
    }

    @RequestMapping(value = "/api/announcement/announcement",method = RequestMethod.DELETE)
    public AnnouncementType deleteAnnouncement(int announcementID){
        return announcement.deleteAnnouncement(announcementID);
    }

    @RequestMapping(value = "/api/announcement/announcementList",method = RequestMethod.GET)
    public  List<AnnouncementBo> getAnnouncementList(String title,int page,String userName){
        return announcement.getAnnouncementList(title,page,userName);
    }

}
