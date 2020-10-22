package com.imbus.bank.announcementModule.service.impl;

import com.imbus.bank.announcementModule.bo.AnnouncementBo;
import com.imbus.bank.announcementModule.bo.AnnouncementDetailBo;
import com.imbus.bank.announcementModule.dao.AnnouncementDao;
import com.imbus.bank.announcementModule.entity.AnnouncementEntity;
import com.imbus.bank.announcementModule.service.IAnnouncement;
import com.imbus.bank.announcementModule.type.AnnouncementType;
import com.imbus.bank.common.UserCommon;
import com.imbus.bank.utils.PageDivideUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementImpl implements IAnnouncement{
    @Autowired
    AnnouncementDao announcementDao;

    private final int ROWS_ONE_PAGE = 5;

    @Override
    public int getAnnouncementPageNumber(AnnouncementEntity announcementEntity){
        int announcementNumber = announcementDao.getAnnouncementNumber(announcementEntity);
        return PageDivideUtil.getCountOfPages(announcementNumber,ROWS_ONE_PAGE);
    }

    @Override
    public AnnouncementType addAnnouncement(AnnouncementEntity announcementEntity) {
        int userID = UserCommon.getUserBo().getUserID();
        announcementEntity.setUserID(userID);
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
    public List<AnnouncementBo> getAnnouncementList(String title, int page,String userName) {
        int startRow = (page -1) * ROWS_ONE_PAGE;
        return announcementDao.getAnnouncementList(title,startRow,ROWS_ONE_PAGE,userName);
    }

    @Override
    public AnnouncementDetailBo getAnnouncementDetail(int announcementID){
        return announcementDao.getAnnouncementDetail(announcementID);
    }


}
