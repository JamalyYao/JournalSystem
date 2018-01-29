package com.zhongfucheng.service.impl;

import com.zhongfucheng.domain.Journal;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.service.JournalService;
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
public class JournalServiceImplTest {


    @Autowired
    private JournalService journalService;

    @Test
    public void saveBlog() {

        Journal journal = new Journal();
        journal.setAuthor("zhognfucheng");
        journal.setCreateTime(new Date());

        User user = new User();
        user.setUserId(1);

        journal.setUser(user);

        journal.setContent("happy ");
        journal.setTitle("hello world");

        journalService.saveJournal(journal);


    }

    @Test
    public void paginateAndSort() {
        User user = new User();
        user.setUserId(1);
        Page<Journal> blogs = journalService.paginateAndSort(0, user);


        System.out.println(blogs);

    }
}