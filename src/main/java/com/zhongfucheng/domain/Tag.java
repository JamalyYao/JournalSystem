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

    private static final long serialVersionUID = 1L;

    /** 标签id主键. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 用户id外键. */
    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId")
    private String userId = "";

    /** 标签名字. */
    private String tagName = "";

    /** 文章Id外键. */
    @ManyToOne(targetEntity = Blog.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "blogId")
    private String blogId = "";

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }
}
