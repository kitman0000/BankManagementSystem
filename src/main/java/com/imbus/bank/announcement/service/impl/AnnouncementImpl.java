package com.imbus.bank.announcement.service.impl;

import com.imbus.bank.announcement.entity.AnnouncementEntity;
import com.imbus.bank.announcement.service.IAnnouncement;
import com.imbus.bank.announcement.type.Announcement;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementImpl implements IAnnouncement{
    @Override
    public Announcement addAnnouncement(AnnouncementEntity announcementEntity) {
        return null;
    }

    @Override
    public Announcement editAnnouncement(AnnouncementEntity announcementEntity) {
        return null;
    }

    @Override
    public Announcement deleteAnnouncement(int announcementID) {
        return null;
    }
}
