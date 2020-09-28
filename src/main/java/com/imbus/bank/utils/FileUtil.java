package com.imbus.bank.utils;

import com.imbus.bank.emailModule.entity.FilenameEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by zhong on 2020-4-13.
 */
@Component
public class FileUtil {

    static private String fileSavePath;

    @Value("${fileSavePath}")
    public void setFileSavePath(String fileSavePath) {
        FileUtil.fileSavePath = fileSavePath;
    }

    /**
     * 保存前端上传过来的文件
     * @param multipartFile 文件对象
     * @return 文件名
     * @throws IOException IO异常
     */
    public static String saveFile(MultipartFile multipartFile,String path) throws IOException{
        // 检查path是否存在
        File pathFile = new File(fileSavePath + path);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss-sss");
        String timeStamp = df.format(new Date());

        String filename = timeStamp + multipartFile.getOriginalFilename();
        File file = new File( fileSavePath + path + filename);
        multipartFile.transferTo(file);

        return filename;
    }
}
