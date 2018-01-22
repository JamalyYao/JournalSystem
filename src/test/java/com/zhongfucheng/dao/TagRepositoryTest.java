package com.zhongfucheng.dao;

import com.zhongfucheng.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ozc on 2018/1/17.
 *
 * @author ozc
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TagRepositoryTest {


    @Autowired
    private TagRepository tagRepository;

    @Test
    public void findDistinctByUser() {

        User user = new User();
        user.setUserId(1);

        List<String> distinctByUser = tagRepository.selectTagNames(user);

        System.out.println(distinctByUser);

    }

    @Test
    public void selectTagNamesAndCount() {

        User user = new User();
        user.setUserId(1);

        List<String> distinctByUser = tagRepository.selectTagNames(user);
    }
}