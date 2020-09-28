package com.imbus.bank.announcement.service;

import com.imbus.bank.announcement.entity.AnnouncementEntity;
import com.imbus.bank.announcement.type.Announcement;

public interface IAnnouncement {
    Announcement addAnnouncement(AnnouncementEntity announcementEntity);

    Announcement editAnnouncement(AnnouncementEntity announcementEntity);

    Announcement deleteAnnouncement(int announcementID);

}
