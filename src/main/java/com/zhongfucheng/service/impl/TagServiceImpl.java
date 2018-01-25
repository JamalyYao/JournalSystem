package com.zhongfucheng.service.impl;

import com.zhongfucheng.dao.TagRepository;
import com.zhongfucheng.domain.Journal;
import com.zhongfucheng.domain.Tag;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.LabelStatistics;
import com.zhongfucheng.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ozc on 2018/1/15.
 *
 * @author ozc
 * @version 1.0
 */
@Service
public class TagServiceImpl implements TagService {


    @Autowired
    private TagRepository tagRepository;


    @Transactional
    @Override
    public Journal saveTags(List<Tag> tags) {
        //JPA支持批量插入
        tagRepository.save(tags);
        return null;
    }

    @Override
    public List<String> selectTagNames(User user) {
        return tagRepository.selectTagNames(user);
    }

    @Override
    public List<LabelStatistics> selectTagNamesAndCount(User user) {

        List<Object[]> objects = tagRepository.selectTagNamesAndCount(user);

        //存储统计标签的List
        List<LabelStatistics> list = new ArrayList<>();

        for (Object[] object : objects) {
            LabelStatistics labelStatistics = new LabelStatistics();
            labelStatistics.setTagName(object[0].toString());
            labelStatistics.setBlogCount(object[1].toString());

            list.add(labelStatistics);
        }

        return list;
    }

    @Transactional
    @Override
    public void deleteTagByTagName(String tagName) {
        tagRepository.deleteTagByTagName(tagName);

    }


    @Transactional
    @Override
    public void updateTag(String oldVal, String newVal) {
        tagRepository.updateTag(oldVal, newVal);


    }


    @Transactional
    @Override
    public void deleteTagByBlog(Journal journal) {
        tagRepository.deleteTagsByJournal(journal);

    }


}
