package com.imbus.bank.announcementModule.service;

import com.imbus.bank.announcementModule.bo.AnnouncementBo;
import com.imbus.bank.announcementModule.bo.AnnouncementDetailBo;
import com.imbus.bank.announcementModule.entity.AnnouncementEntity;
import com.imbus.bank.announcementModule.type.AnnouncementType;

import java.util.List;

public interface IAnnouncement {
    int getAnnouncementPageNumber(AnnouncementEntity announcementEntity);

    AnnouncementType addAnnouncement(AnnouncementEntity announcementEntity);

    List<AnnouncementBo> getAnnouncementList(String title, int page, String userName);

    AnnouncementDetailBo getAnnouncementDetail(int announcementID);

    AnnouncementType editAnnouncement(AnnouncementEntity announcementEntity);

    AnnouncementType deleteAnnouncement(int announcementID);
}
