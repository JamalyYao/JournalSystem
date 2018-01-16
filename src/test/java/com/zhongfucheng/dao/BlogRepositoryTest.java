package com.zhongfucheng.dao;

import com.zhongfucheng.domain.Blog;
import com.zhongfucheng.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ozc on 2018/1/16.
 *
 * @author ozc
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BlogRepositoryTest {


    @Autowired
    private BlogRepository blogRepository;

    @Test
    public void findBlogsByUser() {


        User user = new User();
        user.setUserId(1);
        List<Blog> blogsByUser = blogRepository.findBlogsByUser(user);
        System.out.println(blogsByUser);

    }
}