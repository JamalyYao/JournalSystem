package com.zhongfucheng.service;

import com.zhongfucheng.domain.Blog;
import com.zhongfucheng.domain.Tag;
import com.zhongfucheng.domain.User;

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
    List<Tag> selectTagNamesAndCount(User user);

}
