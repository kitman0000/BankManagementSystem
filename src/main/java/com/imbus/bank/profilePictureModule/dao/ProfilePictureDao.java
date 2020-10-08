package com.imbus.bank.profilePictureModule.dao;

import com.imbus.bank.profilePictureModule.bo.ProfilePictureBo;
import com.imbus.bank.profilePictureModule.entity.ProfilePictureEntity;

import java.util.List;

public interface ProfilePictureDao {
    void addProfilePicture(ProfilePictureEntity profilePictureEntity);

    List<ProfilePictureBo> getProfilePicture(int userID);
}
