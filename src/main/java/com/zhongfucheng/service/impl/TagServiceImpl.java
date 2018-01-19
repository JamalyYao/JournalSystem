package com.zhongfucheng.service.impl;

import com.zhongfucheng.dao.TagRepository;
import com.zhongfucheng.domain.Blog;
import com.zhongfucheng.domain.Tag;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Blog saveTags(List<Tag> tags) {

        if (tags != null) {
            for (Tag tag : tags) {
                tagRepository.save(tag);
            }
        }
        return null;
    }

    @Override
    public List<String> selectTagNames(User user) {
        return tagRepository.selectTagNames(user);
    }


}
