package com.zhongfucheng.service;

import com.zhongfucheng.domain.Music;
import com.zhongfucheng.domain.User;

import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */
public interface MusicService {


    /**
     * 添加音乐信息到数据库中
     */
    Music saveMusic(Music music);

    /**
     * 获取用户下的所有音乐
     */
    List<Music> findMusicByUser(User user);

    void deleteMusicById(Integer musicId);





}
