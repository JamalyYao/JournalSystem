package com.zhongfucheng.dao;

import com.zhongfucheng.domain.Tag;
import com.zhongfucheng.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */
public interface TagRepository extends JpaRepository<Tag, Integer>, JpaSpecificationExecutor<Tag> {

    /**
     * @param user
     * @return
     * @descript: 查询当前用户下所有的标签(去除重复的)
     */

    //@Query后面应该是跟实体类名,字段也要用实体类里面定义的字段.如:表名:user,类名:User,那就得用User,表字段名user_name,实体字段:userName,那么用的时候就得用userName
    @Query("SELECT  DISTINCT(t.tagName) from  Tag t WHERE t.user = :user")
    List<String> selectTagNames(@Param("user") User user);


    /**
     * 查询标签、并统计该标签的文章数
     *
     * @param user
     * @return
     */
    @Query("SELECT t.tagName, count(t.tagName) FROM Tag t WHERE t.user = :user GROUP BY t.tagName")
    List<Object[]> selectTagNamesAndCount(@Param("user") User user);


    /**
     * 根据标签名删除标签
     */
    void deleteTagByTagName(String tagName);


    /**
     * 更新标签的名字
     *
     * @param oldVal
     * @param newVal
     */
    @Modifying
    @Query("update Tag t set t.tagName= :newVal where t.tagName=:oldVal")
    void updateTag(@Param("oldVal") String oldVal, @Param("newVal") String newVal);


}
