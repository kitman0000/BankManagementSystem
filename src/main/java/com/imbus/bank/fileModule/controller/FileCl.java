package com.imbus.bank.fileModule.controller;

import com.imbus.bank.fileModule.service.IFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhong on 2020-8-13.
 */
@Api(value = "文件传输接口")
@RestController
public class FileCl {

    @Autowired
    private IFile file;

    @ApiOperation(value = "上传文件",notes = "path必须以/开头")
    @RequestMapping(value = "/api/file/file",method = RequestMethod.POST)
    public String upload(MultipartFile multipartFile,String path){
        return file.upload(multipartFile,path);
    }
}
