package com.imbus.bank.fileModule.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhong on 2020-8-13.
 */
public interface IFile {
    String upload(MultipartFile multipartFile, String path);
}
