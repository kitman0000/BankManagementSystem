package com.imbus.bank.profilePictureModule.controller;

import com.imbus.bank.profilePictureModule.bo.ProfilePictureBo;
import com.imbus.bank.profilePictureModule.entity.ProfilePictureEntity;
import com.imbus.bank.profilePictureModule.service.IProfilePicture;
import com.imbus.bank.profilePictureModule.type.ProfilePictureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfilePictureCl {
    @Autowired
    IProfilePicture profilePicture;

    @RequestMapping(value = "/api/profilePicture",method = RequestMethod.POST)
    public ProfilePictureType addProfilePicture(ProfilePictureEntity profilePictureEntity) {
        profilePicture.addProfilePicture(profilePictureEntity);
        return ProfilePictureType.HANDLE_PICTURE_SUCCESS;
    }

    @RequestMapping(value = "/api/profilePicture",method = RequestMethod.GET)
    public List<ProfilePictureBo> getProfilePicture(int userID) {
        return profilePicture.getProfilePicture(userID);
    }

}
