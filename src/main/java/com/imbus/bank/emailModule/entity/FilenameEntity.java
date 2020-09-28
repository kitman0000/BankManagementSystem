package com.imbus.bank.emailModule.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by zhong on 2020-4-13.
 */
@Data
public class FilenameEntity {
    List<String> filenameList;

    public void addFilename(String fileName){
        filenameList.add(fileName);
    }
}
