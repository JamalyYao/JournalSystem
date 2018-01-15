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
public class Tag implements Serializable {


    // TODO 还是没找到哪里出错了。

    /** 标签id主键. */
    @Id
    @GeneratedValue
    private Integer tagId;

    /** 用户id外键. */
    @ManyToOne(targetEntity = com.zhongfucheng.domain.User.class, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private Integer  userId ;

    /** 标签名字. */
    private String tagName = "";

    /** 文章Id外键. */
    @ManyToOne(targetEntity = com.zhongfucheng.domain.Blog.class, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "blog_id")
    private Integer  blogId ;


    public Tag() {
    }


    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }
}
