package com.zhongfucheng.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ozc on 2018/1/10.
 *
 * @author ozc
 * @version 1.0
 *
 *
 * 生成上传头像的缩略图
 */

@Service
public class ThumbnailService {



    public String thumbnail(MultipartFile file, String uploadPath, String realUploadPath) {

        try {
            String des = realUploadPath + "/thum" + file.getOriginalFilename();

        } catch (Exception e) {
            // TODO: handle exception
        }

        return uploadPath + "/thum" + file.getOriginalFilename();
    }
}






