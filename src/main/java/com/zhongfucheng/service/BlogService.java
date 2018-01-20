package com.zhongfucheng.service;

import com.zhongfucheng.domain.Blog;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.ArchiveRecords;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */
public interface BlogService {

    Blog saveBlog(Blog blog);


    /**
     * 根据用户id获取所有的文章
     * @param user
     * @return
     */
    List<Blog> selectAllBlog(User user);


    /**
     * 文章存档数据查询(在当前用户下)
     *
     * @return
     */
    List<ArchiveRecords> selectArchiveRecords(String userId);

    /**
     * 根据时间来查询对应的日志
     */

    List<Blog> selectBlogByTime(Date start, Date end);


    /**
     * 根据标签名来查询对应的文章
     */

    List<Blog> selectBlogByTag(String tagName);

    /**
     * 分页排序文章(无条件)
     */
    Page<Blog> paginateAndSort(Integer currentPage);

    /**
     * 根据Id查询文章的详情
     */
    Blog findBlogDetailById(Integer blogId);

    /**
     * 分页+条件查询文章的数据
     */
    Page<Blog> paginationAndConditionBlog();












}
