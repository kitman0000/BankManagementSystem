package com.imbus.bank.announcement.dao;

import com.imbus.bank.announcement.entity.AnnouncementEntity;
import org.apache.ibatis.annotations.Param;

public interface AnnouncementDao {
    void addAnnouncement(@Param("AnnoucementEntity") AnnouncementEntity announcementEntity);

    void editAnnouncement(@Param("AnnoucementEntity") AnnouncementEntity announcementEntity);

    void deleteAnnouncement(int announcementID);
}
