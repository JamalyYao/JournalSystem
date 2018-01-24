package com.zhongfucheng.service;

import com.zhongfucheng.domain.Blog;
import com.zhongfucheng.domain.Tag;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.LabelStatistics;

import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */
public interface TagService {


    //保存Tags
    Blog saveTags(List<Tag> tag);

    //查询当前用户下所有不同名字的标签
    List<String> selectTagNames(User user);


    /**
     * 查询标签、并统计该标签的文章数
     *
     * @param user
     * @return
     */
    List<LabelStatistics> selectTagNamesAndCount(User user);


    /**
     * 根据标签名删除标签
     *
     * @param tagName
     */
    void deleteTagByTagName(String tagName);


    /**
     * 更新标签的名字
     *
     * @param oldVal
     * @param newVal
     */
    void updateTag(String oldVal, String newVal);

    /**
     * 根据文章Id删除其标签
     * @param blog
     */
    void deleteTagByBlog(Blog blog);

}
