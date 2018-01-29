package com.zhongfucheng.controller;

import com.zhongfucheng.domain.Music;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.Result;
import com.zhongfucheng.service.MusicService;
import com.zhongfucheng.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */

@RestController
public class MusicController {

    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private MusicService musicService;


    /**
     * 获取用户下的所有音乐
     */
    @GetMapping("/user/music")
    public Result<Music> selectMusicByUser(HttpSession session) {
        User user = (User) session.getAttribute("user");

        List<Music> musicByUser = musicService.findMusicByUser(user);

        return ResultUtil.success(musicByUser);

    }

    /**
     * 删除某音乐
     *
     * @param musicId
     * @return
     */
    @DeleteMapping(value = "/music/{musicId}", produces = {"application/json;charset=UTF-8"})
    public Result deleteMusic(@PathVariable("musicId") String musicId) {

        musicService.deleteMusicById(Integer.valueOf(musicId));

        return ResultUtil.success();
    }

}
