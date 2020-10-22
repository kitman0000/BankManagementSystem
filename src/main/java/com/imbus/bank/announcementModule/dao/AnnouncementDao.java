package com.imbus.bank.announcementModule.dao;

import com.imbus.bank.announcementModule.bo.AnnouncementDetailBo;
import com.imbus.bank.announcementModule.entity.AnnouncementEntity;
import com.imbus.bank.announcementModule.bo.AnnouncementBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface AnnouncementDao {
    int getAnnouncementNumber(@Param("announcementEntity") AnnouncementEntity announcementEntity);

    void addAnnouncement(@Param("announcementEntity") AnnouncementEntity announcementEntity);

    AnnouncementDetailBo getAnnouncementDetail(@Param("announcementID")int announcementID);

    void editAnnouncement(@Param("announcementEntity") AnnouncementEntity announcementEntity);

    void deleteAnnouncement(@Param("announcementID")int announcementID);

    List<AnnouncementBo> getAnnouncementList(@Param("title") String title, @Param("startRow") int startRow,@Param("rowsOnePage") int rowsOnePage,@Param("userName") String userName);
}
