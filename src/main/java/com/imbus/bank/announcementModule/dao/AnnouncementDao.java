package com.imbus.bank.announcementModule.dao;

import com.imbus.bank.announcementModule.entity.AnnouncementEntity;
import com.imbus.bank.announcementModule.bo.AnnouncementBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface AnnouncementDao {
    void addAnnouncement(@Param("annoucementEntity") AnnouncementEntity announcementEntity);

    List<AnnouncementBo> getAnnouncementDetail(@Param("annoucementID")int announcementID);

    void editAnnouncement(@Param("annoucementEntity") AnnouncementEntity announcementEntity);

    void deleteAnnouncement(@Param("annoucementID")int announcementID);

    List<AnnouncementBo> getAnnouncementList(@Param("annoucementEntity") AnnouncementEntity announcementEntity);
}
