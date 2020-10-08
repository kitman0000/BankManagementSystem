package com.imbus.bank.announcementModule.service.impl;

import com.imbus.bank.announcementModule.bo.AnnouncementBo;
import com.imbus.bank.announcementModule.dao.AnnouncementDao;
import com.imbus.bank.announcementModule.entity.AnnouncementEntity;
import com.imbus.bank.announcementModule.service.IAnnouncement;
import com.imbus.bank.announcementModule.type.AnnouncementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementImpl implements IAnnouncement{
    @Autowired
    AnnouncementDao announcementDao;


    @Override
    public AnnouncementType addAnnouncement(AnnouncementEntity announcementEntity) {
        announcementDao.addAnnouncement(announcementEntity);
        return AnnouncementType.HANDLE_ANNOUNCEMENT_SUCCESS;
    }

    @Override
    public AnnouncementType editAnnouncement(AnnouncementEntity announcementEntity) {
        announcementDao.editAnnouncement(announcementEntity);
        return AnnouncementType.HANDLE_ANNOUNCEMENT_SUCCESS;
    }

    @Override
    public AnnouncementType deleteAnnouncement(int announcementID) {
        announcementDao.deleteAnnouncement(announcementID);
        return AnnouncementType.HANDLE_ANNOUNCEMENT_SUCCESS;
    }

    @Override
    public List<AnnouncementBo> getAnnouncementList(AnnouncementEntity announcementEntity) {
        return announcementDao.getAnnouncementList(announcementEntity);
    }

    @Override
    public List<AnnouncementBo> getAnnouncementDetail(int announcementID){
        return announcementDao.getAnnouncementDetail(announcementID);
    }


}
