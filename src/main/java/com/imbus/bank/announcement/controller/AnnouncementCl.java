package com.imbus.bank.announcement.controller;


import com.imbus.bank.announcement.entity.AnnouncementEntity;
import com.imbus.bank.announcement.service.IAnnouncement;
import com.imbus.bank.announcement.type.Announcement;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "公告接口")
@RestController
public class AnnouncementCl {
    @Autowired
    IAnnouncement announcement;

    @RequestMapping(value = "/api/announcement/announcement",method = RequestMethod.POST)
    public Announcement addAnnouncement(AnnouncementEntity announcementEntity){
        return announcement.addAnnouncement(announcementEntity);
    }

    @RequestMapping(value = "/api/announcement/announcement",method = RequestMethod.PUT)
    public Announcement editAnnouncement(AnnouncementEntity announcementEntity){
        return announcement.editAnnouncement(announcementEntity);
    }

    @RequestMapping(value = "/api/announcement/announcement",method = RequestMethod.DELETE)
    public Announcement deleteAnnouncement(int announcementID){
        return announcement.deleteAnnouncement(announcementID);
    }



}
