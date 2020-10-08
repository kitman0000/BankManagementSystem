package com.imbus.bank.profilePictureModule.dao;

import com.imbus.bank.profilePictureModule.bo.ProfilePictureBo;
import com.imbus.bank.profilePictureModule.entity.ProfilePictureEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProfilePictureDao {
    void addProfilePicture(ProfilePictureEntity profilePictureEntity);

    List<ProfilePictureBo> getProfilePicture(int userID);
}
