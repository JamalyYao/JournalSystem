package com.zhongfucheng.controller;

import com.zhongfucheng.domain.Blog;
import com.zhongfucheng.domain.Tag;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.ArchiveRecords;
import com.zhongfucheng.dto.Result;
import com.zhongfucheng.enums.ResultEnum;
import com.zhongfucheng.exception.UserException;
import com.zhongfucheng.service.BlogService;
import com.zhongfucheng.service.TagService;
import com.zhongfucheng.service.UserService;
import com.zhongfucheng.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */

@Controller
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
    @ResponseBody
    public Result saveBlog(HttpSession session, Blog blog, String tags) {

        //得到当前用户信息
        User user = (User) session.getAttribute("user");

        //设置Blog的相关数据，保存Blog
        blog.setAuthor(user.getUserNickName());
        blog.setCreateTime(new Date());
        blog.setUser(user);
        Blog saveBlog = blogService.saveBlog(blog);

        System.out.println(tags);


        //解析页面带过来的Tags，保存Tag
        if (tags != null && tags.length() > 0) {
            String[] eachTag = tags.split(",");
            List<Tag> tagList = new ArrayList<>();
            for (String tag : eachTag) {
                Tag tag1 = new Tag();
                tag1.setUser(user);
                tag1.setTagName(tag);
                tag1.setBlog(saveBlog);
                tagList.add(tag1);
            }
            tagService.saveTags(tagList);
        }
        return ResultUtil.success(user);

    }


    /**
     * 查询用户下所有博客以及相关信息
     *
     * @return
     */
    @GetMapping(value = "/blogs")
    public String selectBlogs(HttpSession session, Model model) {

        List<Blog> blogs = null;
        List<ArchiveRecords> archiveRecords = null;
        List<String> tags = null;

        //查询博客相关信息
        User user = (User) session.getAttribute("user");
        blogs = blogService.selectAllBlog(user);

        //查询文章存档信息
        archiveRecords = blogService.selectArchiveRecords();

        //查询标签
        tags = tagService.selectTagNames(user);

        model.addAttribute("blogs", blogs);
        model.addAttribute("archiveRecords", archiveRecords);
        model.addAttribute("tags", tags);

        return "/journal";

    }


    /**
     * 根据时间查询用户下博客
     *
     * @return
     */
    @GetMapping(value = "/blogs/{year}/{month}")
    public String selectBlogsByDate(@PathVariable("year") Integer year, @PathVariable("month") Integer month, HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        List<Blog> blogs = null;
        List<ArchiveRecords> archiveRecords = null;
        List<String> tags = null;

        //解析日期，根据时间查询相关文章
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
            Date startDate = sdf.parse(year + "-" + month + "-1");
            Date endDate = sdf.parse(year + "-" + month + "-31");
            blogs = blogService.selectBlogByTime(startDate, endDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new UserException(ResultEnum.UNKONW_ERROR);
        }


        //查询文章存档信息
        archiveRecords = blogService.selectArchiveRecords();

        //查询标签
        tags = tagService.selectTagNames(user);
        System.out.println(tags);

        model.addAttribute("blogs", blogs);
        model.addAttribute("archiveRecords", archiveRecords);
        model.addAttribute("tags", tags);

        return "/journal";

    }

}
