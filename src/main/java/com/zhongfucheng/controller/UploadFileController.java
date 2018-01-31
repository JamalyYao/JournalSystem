package com.zhongfucheng.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.zhongfucheng.domain.Music;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.FilePath;
import com.zhongfucheng.dto.Result;
import com.zhongfucheng.service.MusicService;
import com.zhongfucheng.utils.ResultUtil;
import com.zhongfucheng.utils.WebUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UploadFileController {


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


    @Autowired
    private MusicService musicService;


    /**
     * 使用jersy的客户端上传资源
     */

    public void uploadBytesByJersy(String realPath, byte[] bytes) {

        //创建jersy的客户端
        Client client = Client.create();
        //创建web资源对象
        WebResource wr = client.resource(realPath);
        //拿到文件的二进制数据，使用web资源对象上传
        wr.put(bytes);
    }

    /**
     * 获取一个随机的文件名
     *
     * @param originalFilename
     * @return
     */
    public String createRandomName(String originalFilename) {
        //上传文件的名字是不能相同的，因此我们设置一下文件的名称
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            fileName = fileName + random.nextInt(10);

        }
        //获取该文件的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String result = fileName + suffix;

        return result;


    }

    /**
     * 上传MP3音乐
     *
     * @param musicFile
     * @throws IOException
     */
    @PostMapping(value = "/musicFile", produces = {"application/json;charset=UTF-8"})
    public Result uploadMusic(@RequestParam MultipartFile musicFile, HttpSession session) throws IOException {

        User user = (User) session.getAttribute("user");


        //拿到该文件的原始名称，得到一个随机的文件名
        String originalFilename = musicFile.getOriginalFilename();
        String randomName = createRandomName(originalFilename);


        /***
         * 绝对路径是留给页面src属性做显示的
         * 相对路径是保存在数据库中，通过input来进行提交的。
         */
        //获得上传文件的绝对路径
        String realPath = filePath + "/music/" + randomName;

        //获得相对路径
        String relativePath = "/music/" + randomName;



        //上传资源
        uploadBytesByJersy(realPath, musicFile.getBytes());


        //将音乐信息到数据库中保存
        Music music = new Music();
        music.setMusicName(originalFilename);
        music.setMusicPath(relativePath);
        music.setUser(user);
        musicService.saveMusic(music);

        return ResultUtil.success(new FilePath(realPath, relativePath, originalFilename));

    }


    /**
     * 上传头像
     *
     * @param imgsFile
     * @throws IOException
     */
    @PostMapping(value = "/image", produces = {"application/json;charset=UTF-8"})
    public Result uploadPic(@RequestParam MultipartFile imgsFile, HttpSession session) throws IOException {


        //拿到该文件的原始名称，得到一个随机的文件名
        String originalFilename = imgsFile.getOriginalFilename();
        String randomName = createRandomName(originalFilename);

        //获取该文件的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        /***
         * 绝对路径是留给页面src属性做显示的
         * 相对路径是保存在数据库中，通过input来进行提交的。
         */
        //获得上传文件的绝对路径
        String realPath = filePath + "/upload/" + randomName;

        //获得相对路径
        String relativePath = "/upload/" + randomName;

        //缩略图的生成
        BufferedImage bufferedImage = Thumbnails.of(imgsFile.getInputStream()).size(WIDTH, HEIGHT).asBufferedImage();


        //将BufferImage转成byte进行上传
        byte[] bytes = WebUtils.imageToBytes(bufferedImage, suffix.substring(1, suffix.length()));

        //上传资源
        uploadBytesByJersy(realPath, bytes);


        return ResultUtil.success(new FilePath(realPath, relativePath, originalFilename));

    }


    /**
     * 上传图片(富文本编辑器)
     *
     * @param imgsFile
     * @throws IOException
     */
    @RequestMapping(value = "/upload", produces = {"application/json;charset=UTF-8"})
    public Map uploadPicFile(@RequestParam MultipartFile imgsFile, HttpSession session) throws IOException {

        //拿到该文件的原始名称，得到一个随机的文件名
        String originalFilename = imgsFile.getOriginalFilename();
        String randomName = createRandomName(originalFilename);


        /***
         * 绝对路径是留给页面src属性做显示的
         * 相对路径是保存在数据库中，通过input来进行提交的。
         */
        //获得上传文件的绝对路径
        String realPath = filePath + "/upload/" + randomName;


        //上传资源
        uploadBytesByJersy(realPath, imgsFile.getBytes());

        //返回的JSON要符合富文本编辑器：
        List<String> list = new ArrayList();
        list.add(realPath);
        Map map = new HashMap();
        map.put("errno", 0);
        map.put("data", list);

        return map;

    }
}
