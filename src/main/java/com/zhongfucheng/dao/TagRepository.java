package com.zhongfucheng.dao;

import com.zhongfucheng.domain.Tag;
import com.zhongfucheng.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {


    /**
     * @param user
     * @return
     * @descript: 查询当前用户下所有的标签(去除重复的)
     */

    //@Query后面应该是跟实体类名,字段也要用实体类里面定义的字段.如:表名:user,类名:User,那就得用User,表字段名user_name,实体字段:userName,那么用的时候就得用userName
    @Query("SELECT  DISTINCT(t.tagName) from  Tag t WHERE t.user = :user")
    List<String> selectTagNames(@Param("user") User user);



}
