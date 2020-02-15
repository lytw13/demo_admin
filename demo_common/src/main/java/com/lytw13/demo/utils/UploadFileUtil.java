package com.lytw13.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadFileUtil {

    public static void upload(MultipartFile file) {
        // 拿到文件名
        String filename = file.getOriginalFilename();
        // 存放上传图片的文件夹
        String fileDirPath = new String("demo_web/src/main/resources/static/img");
        File fileDir = new File(fileDirPath);
        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
            // 上传图片到 -》 “绝对路径”
            file.transferTo(newFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}