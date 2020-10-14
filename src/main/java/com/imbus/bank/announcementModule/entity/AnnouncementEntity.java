package com.imbus.bank.announcementModule.entity;

import lombok.Data;

@Data
public class AnnouncementEntity {
    private int id;

    private int userID;

    private String title;

    private String userName;

    private String content;
}
