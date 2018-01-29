package com.zhongfucheng.domain;

import javax.persistence.*;

/**
 * Created by ozc on 2018/1/29.
 *
 * @author ozc
 * @version 1.0
 */


@Entity
@Table(name = "t_music")
public class Music {

    private Integer musicId;
    private String musicName = "";
    private String musicPath = "";

    private User user;


    @Id
    @GeneratedValue
    public Integer getMusicId() {
        return musicId;
    }


    /**
     * 多首歌曲对应一个用户
     * @return
     */
    @ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }


    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }


    public void setUser(User user) {
        this.user = user;
    }
}
