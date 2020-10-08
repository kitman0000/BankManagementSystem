package com.imbus.bank.profilePictureModule.service.impl;

import com.imbus.bank.profilePictureModule.bo.ProfilePictureBo;
import com.imbus.bank.profilePictureModule.dao.ProfilePictureDao;
import com.imbus.bank.profilePictureModule.entity.ProfilePictureEntity;
import com.imbus.bank.profilePictureModule.service.IProfilePicture;
import com.imbus.bank.profilePictureModule.type.ProfilePictureType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProfilePictureImpl implements IProfilePicture {
    @Autowired
    ProfilePictureDao profilePictureDao;

    @Override
    public ProfilePictureType addProfilePicture(ProfilePictureEntity profilePictureEntity) {
        profilePictureDao.addProfilePicture(profilePictureEntity);
        return ProfilePictureType.HANDLE_PICTURE_SUCCESS;
    }

    @Override
    public List<ProfilePictureBo> getProfilePicture(int userID){
        return profilePictureDao.getProfilePicture(userID);
    }

}
