package com.zhongfucheng.dto;

import java.io.Serializable;

/**
 * Created by ozc on 2018/1/23.
 *
 * @author ozc
 * @version 1.0
 *
 * 封装了标签的统计信息(该标签有多少篇文章)
 */
public class LabelStatistics implements Serializable {


    private String tagName;
    private String  blogCount;




    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(String blogCount) {
        this.blogCount = blogCount;
    }
}
