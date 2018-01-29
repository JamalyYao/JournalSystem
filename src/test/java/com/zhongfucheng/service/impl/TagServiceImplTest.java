package com.zhongfucheng.service.impl;

import com.zhongfucheng.domain.Journal;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.LabelStatistics;
import com.zhongfucheng.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ozc on 2018/1/23.
 *
 * @author ozc
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TagServiceImplTest {


    @Autowired
    private TagService tagService;
    @Test
    public void selectTagNamesAndCount() {

        User user = new User();
        user.setUserId(1);


        List<LabelStatistics> list = tagService.selectTagNamesAndCount(user);


        System.out.println(list);

    }

    @Test
    public void updateTag() {
        tagService.updateTag("234", "123");



    }

    @Test
    public void deleteTagByBlog() {

        Journal journal = new Journal();
        journal.setJournalId(87);

        tagService.deleteTagByBlog(journal);
        
    }
}