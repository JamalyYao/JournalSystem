package com.zhongfucheng.service.impl;

import com.zhongfucheng.dao.MusicRepository;
import com.zhongfucheng.domain.Music;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ozc on 2018/1/15.
 *
 * @author ozc
 * @version 1.0
 */
@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicRepository musicRepository;


    @Override
    public Music saveMusic(Music music) {
        return musicRepository.save(music);
    }

    @Override
    public List<Music> findMusicByUser(User user) {
        return musicRepository.findMusicByUser(user);
    }

    @Override
    public void deleteMusicById(Integer musicId) {
        musicRepository.delete(musicId);

    }


}
