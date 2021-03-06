package com.zhongfucheng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by ozc on 2018/1/12.
 *
 * @author ozc
 * @version 1.0
 */
@Entity
@Table(name = "t_journal")
public class Journal implements Serializable {

    private Integer journalId;

    /** 内容携带HTML的*/
    private String content;

    /** 内容无携带HTML*/

    private String contentNoHTML;

    private String title = "";

    private String author = "";

    private Date createTime = new Date();

    /** 用户外键. */
    private User user;

    /** 一篇文章对应多个标签 */
    private List<Tag> tagList = new ArrayList<>();

    @Id
    @GeneratedValue
    public Integer getJournalId() {
        return journalId;
    }

    /** 多篇日志对应一个用户*/
    @ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    /**
     * 一篇文章对应多个标签
     */
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH,CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "journal")
    public List<Tag> getTagList() {

        return tagList;
    }


    /**
     * 设置类型为TEXT，不然VARCHAR是不够存的。
     */
    @Column(columnDefinition="TEXT")
    public String getContentNoHTML() {
        return contentNoHTML;
    }
    @Column(columnDefinition="TEXT")
    public String getContent() {
        return content;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setJournalId(Integer journalId) {
        this.journalId = journalId;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    public void setContentNoHTML(String contentNoHTML) {
        this.contentNoHTML = contentNoHTML;
    }
    @Override
    public String toString() {
        return "Journal{" +
                "journalId=" + journalId +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", user=" + user +
                '}';
    }
}
