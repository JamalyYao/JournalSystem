package com.zhongfucheng.service.impl;

import com.zhongfucheng.domain.Blog;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by ozc on 2018/1/15.
 *
 * @author ozc
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BlogServiceImplTest {


    @Autowired
    private BlogService blogService;

    @Test
    public void saveBlog() {

        Blog blog = new Blog();
        blog.setAuthor("zhognfucheng");
        blog.setCreateTime(new Date());

        User user = new User();
        user.setUserId(1);

        blog.setUser(user);

        blog.setContent("happy ");
        blog.setTitle("hello world");

        blogService.saveBlog(blog);


    }

    @Test
    public void paginateAndSort() {


        Page<Blog> blogs = blogService.paginateAndSort(0);

        System.out.println(blogs);

    }
}