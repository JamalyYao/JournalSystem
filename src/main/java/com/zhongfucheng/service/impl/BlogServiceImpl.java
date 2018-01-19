package com.zhongfucheng.service.impl;

import com.zhongfucheng.dao.BlogRepository;
import com.zhongfucheng.domain.Blog;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.ArchiveRecords;
import com.zhongfucheng.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ozc on 2018/1/15.
 *
 * @author ozc
 * @version 1.0
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;


    //每页显示10条
    public static final Integer PAGE_SIZE = 10;



    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> selectAllBlog(User user) {
        return blogRepository.findBlogsByUserOrderByCreateTimeDesc(user);
    }

    @Override
    public List<ArchiveRecords> selectArchiveRecords(String userId) {


        List<Object[]> archiveRecords = blogRepository.findArchiveRecords(userId);

        //将Object[]数组转成DTO实体
        List<ArchiveRecords> archiveRecord = new ArrayList<>();

        for (Object[] record : archiveRecords) {
            ArchiveRecords records = new ArchiveRecords();
            records.setYear(record[0].toString());
            records.setMonth(record[1].toString());
            records.setNum(record[2].toString());

            archiveRecord.add(records);

        }

        return archiveRecord;
    }

    @Override
    public List<Blog> selectBlogByTime(Date start, Date end) {
        return blogRepository.findBlogsByCreateTimeBetweenOrderByCreateTimeDesc(start, end);
    }

    @Override
    public List<Blog> selectBlogByTag(String tagName) {
        return blogRepository.selectBlogByTagName(tagName);
    }

    @Override
    public Page<Blog> paginateAndSort(Integer currentPage) {

        //按照时间来排序(降序)
        Sort sort = new Sort(new Order(Direction.DESC, "createTime"));

        //index是从0开始的，并不是从1开始的
        Pageable pageable = new PageRequest(0, PAGE_SIZE, sort);

        Page<Blog> blogs = blogRepository.findAll(pageable);

        return blogs;
    }

    @Override
    public Blog findBlogDetailById(Integer blogId) {
        return blogRepository.findOne(blogId);
    }



}
