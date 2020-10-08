package com.imbus.bank.profilePictureModule.service;

import com.imbus.bank.profilePictureModule.bo.ProfilePictureBo;
import com.imbus.bank.profilePictureModule.entity.ProfilePictureEntity;
import com.imbus.bank.profilePictureModule.type.ProfilePictureType;

import java.util.List;

public interface IProfilePicture {
    ProfilePictureType addProfilePicture(ProfilePictureEntity profilePictureEntity);

    List<ProfilePictureBo> getProfilePicture(int userID);

}
