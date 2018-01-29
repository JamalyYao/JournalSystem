package com.zhongfucheng.dao;

import com.zhongfucheng.domain.Music;
import com.zhongfucheng.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */
public interface MusicRepository extends JpaRepository<Music, Integer>, JpaSpecificationExecutor<Music> {



    /**
     * 获取用户下的所有音乐
     */
    List<Music> findMusicByUser(User user);




}
