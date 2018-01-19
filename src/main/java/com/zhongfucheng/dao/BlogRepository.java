package com.zhongfucheng.dao;

import com.zhongfucheng.domain.Blog;
import com.zhongfucheng.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    /**
     * 根据用户获取所有的日志,按照日志的时间排序
     * @param user
     * @return
     */
    List<Blog> findBlogsByUserOrderByCreateTimeDesc(User user);

    /**
     * 查询文章存档记录数
     */
    @Query(value = "SELECT t.myYear  AS year, t.monthNo AS month, count(t.monthNo)  AS num FROM ( SELECT month(t_blog.create_time) AS monthNo, year(t_blog.create_time)  AS myYear FROM t_blog WHERE user_id = :userId ) t WHERE  t.myYear = '2018' OR t.myYear = '2017' GROUP BY t.monthNo,t.myYear ORDER BY t.myYear DESC,t.monthNo DESC", nativeQuery = true)
    List<Object[]> findArchiveRecords(@Param("userId") String userId);

    /**
     * 根据日期查询相关的日志(按时间倒序)
     */
    List<Blog> findBlogsByCreateTimeBetweenOrderByCreateTimeDesc(Date start, Date end);


    /**
     * 根据标签查询相关的日志(按时间倒序)
     */
    @Query("SELECT b FROM Tag t ,Blog b WHERE b.blogId = t.blog and t.tagName = :tagName order by b.createTime desc ")
    List<Blog> selectBlogByTagName(@Param("tagName") String tagName);


}
