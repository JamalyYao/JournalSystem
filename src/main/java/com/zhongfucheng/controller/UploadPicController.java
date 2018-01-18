package com.zhongfucheng.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.zhongfucheng.dto.ImagePath;
import com.zhongfucheng.dto.Result;
import com.zhongfucheng.utils.ResultUtil;
import com.zhongfucheng.utils.WebUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ozc on 2018/1/9.
 *
 * @author ozc
 * @version 1.0
 */
@RestController
public class UploadPicController {


    /**
     * 图片的宽度和高度
     */
    public static final int WIDTH = 300;
    public static final int HEIGHT = 160;


    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    //图片服务器的路径
    @Value("${file_path}")
    private String filePath;



    /**
     * 上传头像
     *
     * @param imgsFile
     * @throws IOException
     */
    @PostMapping(value = "/image", produces = {"application/json;charset=UTF-8"})
    public Result uploadPic(@RequestParam MultipartFile imgsFile, HttpSession session) throws IOException {
        //上传文件的名字是不能相同的，因此我们设置一下文件的名称
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
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
        String realPath = filePath + "/upload/" + fileName + suffix;

        //获得相对路径
        String relativePath = "/upload/" + fileName + suffix;

        //缩略图的生成
        BufferedImage bufferedImage = Thumbnails.of(imgsFile.getInputStream()).size(WIDTH, HEIGHT).asBufferedImage();

        //将BufferImage转成byte进行上传
        byte[] bytes = WebUtils.imageToBytes(bufferedImage, suffix.substring(1,suffix.length()));


        //创建jersy的客户端
        Client client = Client.create();
        //创建web资源对象
        WebResource wr = client.resource(realPath);

        //拿到文件的二进制数据，使用web资源对象上传
        wr.put(bytes);


        return ResultUtil.success(new ImagePath(realPath, relativePath));

    }


    /**
     * 上传图片(富文本编辑器)
     *
     * @param imgsFile
     * @throws IOException
     */
    @RequestMapping(value = "/upload", produces = {"application/json;charset=UTF-8"})
    public Map uploadPicFile(@RequestParam MultipartFile imgsFile, HttpSession session) throws IOException {

        //上传文件的名字是不能相同的，因此我们设置一下文件的名称
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
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
        String realPath = filePath + "/upload/" + fileName + suffix;

        //获得相对路径
        String relativePath = "/upload/" + fileName + suffix;

        //创建jersy的客户端
        Client client = Client.create();
        //创建web资源对象
        WebResource wr = client.resource(realPath);
        byte[] bytes = imgsFile.getBytes();
        //拿到文件的二进制数据，使用web资源对象上传
        wr.put(bytes);


        //返回的JSON要符合富文本编辑器：
        List<String> list = new ArrayList();
        list.add(realPath);
        Map map = new HashMap();
        map.put("errno", 0);
        map.put("data", list);

        return map;

    }
}
