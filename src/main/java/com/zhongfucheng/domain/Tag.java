package com.zhongfucheng.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ozc on 2018/1/12.
 *
 * @author ozc
 * @version 1.0
 */
@Entity
@Table(name = "t_tag")
public class Tag implements Serializable {


    private Integer tagId;
    private String tagName ;

    /**
     * 用户id外键.
     */
    private User user;

    /**
     * 文章id外键.
     */
    private Journal journal;

    @Id
    @GeneratedValue
    public Integer getTagId() {
        return tagId;
    }

    // 用户外键
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    //文章外键
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "journal_id")
    public Journal getJournal() {
        return journal;
    }

    public Tag() {
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", user=" + user +
                ", journal=" + journal +
                '}';
    }

}
