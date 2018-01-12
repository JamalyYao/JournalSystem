package com.zhongfucheng.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ozc on 2018/1/12.
 *
 * @author ozc
 * @version 1.0
 */
@Entity
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 文章id主键. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 用户id外键. */

    /**
     * ManyToOne：多对一的配置
     * cascade(级联)：all(所有)，merge(更新)，refresh(查询)，persistence(保存)，remove(删除)
     * fetch: eager:立即加载  one的一方默认是立即加载
     * lazy:懒加载    many的一方默认是懒加载
     * optional:是否可选,外键是否允许为空
     * <p>
     * JoinColumn:指定外键名
     */
    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId")
    private String userId = "";

    /** 文章标题. */
    private String title = "";

    /** 文章作者. */
    private String author = "";

    /** 文章创建时间. */
    private Date create_time = new Date();

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

}
