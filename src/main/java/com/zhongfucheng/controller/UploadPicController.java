package com.zhongfucheng.controller;

import com.sun.jersey.api.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by ozc on 2018/1/9.
 *
 * @author ozc
 * @version 1.0
 */
@RestController
public class UploadPicController {


    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 上传图片
     * @param imgsFile
     * @param writer
     * @throws IOException
     */
    @PostMapping(value = "/image", produces = {"application/json;charset=UTF-8"})
    public void uploadPic(@RequestParam MultipartFile imgsFile, Writer writer) throws IOException {
        //上传文件的名字是不能相同的，因此我们设置一下文件的名称
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        for(int i = 0; i < 3; i++){
            fileName = fileName + random.nextInt(10);
        }
        //拿到该文件的原始名称
        String originalFilename = imgsFile.getOriginalFilename();

        //获取该文件的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        /***
         * 绝对路径是留给页面src属性做显示的
         * 相对路径是保存在数据库中，通过input来进行提交的。
         */
        //获得上传文件的绝对路径
        //String realPath = ResourcesUtils.readProp("file_path")+"/upload/"+fileName+suffix;


        //获得相对路径
        String relativePath = "/upload/"+fileName+suffix;

        //创建jersy的客户端
        Client client = Client.create();
        //创建web资源对象
        //WebResource wr = client.resource(realPath);

        //拿到文件的二进制数据，使用web资源对象上传
        byte[] bytes = imgsFile.getBytes();
        //wr.put(bytes);

        //使用JSON格式把我们的绝对路径和相对路径返回出去。
  /*      JSONObject jo = new JSONObject();
        jo.accumulate("realPath", realPath);
        jo.accumulate("relativePath", relativePath);
        String result = jo.toString();
        System.out.println(result);
        writer.write(result);*/
    }
}
