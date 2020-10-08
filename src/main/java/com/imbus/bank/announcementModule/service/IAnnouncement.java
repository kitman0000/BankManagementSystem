package com.imbus.bank.announcementModule.service;

import com.imbus.bank.announcementModule.bo.AnnouncementBo;
import com.imbus.bank.announcementModule.entity.AnnouncementEntity;
import com.imbus.bank.announcementModule.type.AnnouncementType;

import java.util.List;

public interface IAnnouncement {
    AnnouncementType addAnnouncement(AnnouncementEntity announcementEntity);

    List<AnnouncementBo> getAnnouncementDetail(int announcementID);

    AnnouncementType editAnnouncement(AnnouncementEntity announcementEntity);

    AnnouncementType deleteAnnouncement(int announcementID);

    List<AnnouncementBo> getAnnouncementList(AnnouncementEntity announcementEntity);
}
