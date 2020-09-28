package com.imbus.bank.fileModule.service.impl;

import com.imbus.bank.fileModule.service.IFile;
import com.imbus.bank.fileModule.type.UploadFileResult;
import com.imbus.bank.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhong on 2020-8-13.
 */
@Service
public class FileImpl implements IFile{
    @Override
    public String upload(MultipartFile multipartFile, String path) {

        String filename;

        if(multipartFile != null) {
            try {
                filename = FileUtil.saveFile(multipartFile,path);
            } catch (IOException e) {
                e.printStackTrace();
                return UploadFileResult.UPLOAD_FAILED.toString();
            }
        }else {
            filename = null;
        }
        return filename;
    }
}
