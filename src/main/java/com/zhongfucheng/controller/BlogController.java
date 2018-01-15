package com.zhongfucheng.controller;

import com.zhongfucheng.domain.Blog;
import com.zhongfucheng.domain.Tag;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.Result;
import com.zhongfucheng.service.BlogService;
import com.zhongfucheng.service.TagService;
import com.zhongfucheng.service.UserService;
import com.zhongfucheng.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */

@RestController
public class BlogController {

    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;


    /**
     * 发表博客
     *
     * @param session
     * @return
     */
    @PostMapping(value = "/blog", produces = {"application/json;charset=UTF-8"})
    public Result saveBlog(HttpSession session, Blog blog, String tags) {


        //得到当前用户信息
        User user = (User) session.getAttribute("user");

        //设置Blog的相关数据，保存Blog
        blog.setAuthor(user.getUserNickName());
        blog.setCreate_time(new Date());
        //blog.setUserId(user.getUserId());

        Blog saveBlog = blogService.saveBlog(blog);


        //解析页面带过来的Tags，保存Tag
        String[] eachTag = tags.split(",");
        List<Tag> tagList = new ArrayList<>();
        for (String tag : eachTag) {
            Tag tag1 = new Tag();
            tag1.setBlogId(saveBlog.getBlogId());
            tag1.setTagName(tag);
            tag1.setUserId(user.getUserId());
            tagList.add(tag1);
        }
        tagService.saveTags(tagList);


        return ResultUtil.success();

    }

}
