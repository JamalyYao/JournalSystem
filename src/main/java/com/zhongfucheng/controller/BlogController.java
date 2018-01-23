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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        if (user != null) {
            blogs = blogService.selectAllBlog(user);

            //查询文章存档信息
            archiveRecords = blogService.selectArchiveRecords(String.valueOf(user.getUserId()));

            //查询标签
            tags = tagService.selectTagNames(user);

            // 这里没有做分页，如果大于20篇文章。那么我们只取前20篇
            if (blogs.size() > 20) {
                model.addAttribute("blogs", blogs.subList(0, 20));
            }
            model.addAttribute("blogs", blogs);
            model.addAttribute("archiveRecords", archiveRecords);
            model.addAttribute("tags", tags);
        } else {
            throw new UserException(ResultEnum.USER_NOEXIST);
        }


        return "/journal";

    }


    /**
     * 根据标签查询用户下博客
     *
     * @return
     */
    @GetMapping(value = "/blogs/{tag}")
    public String selectBlogsByTag(@PathVariable("tag") String tag, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        List<Blog> blogs = null;
        List<ArchiveRecords> archiveRecords = null;
        List<String> tags = null;


        //根据标签名查询出对应的文章
        if (tag != null) {
            blogs = blogService.selectBlogByTag(tag);
        }

        //查询文章存档信息
        archiveRecords = blogService.selectArchiveRecords(String.valueOf(user.getUserId()));


        //查询标签
        tags = tagService.selectTagNames(user);

        // 这里没有做分页，如果大于20篇文章。那么我们只取前20篇
        if (blogs.size() > 20) {
            model.addAttribute("blogs", blogs.subList(0, 20));
        }
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
        archiveRecords = blogService.selectArchiveRecords(String.valueOf(user.getUserId()));


        //查询标签
        tags = tagService.selectTagNames(user);

        // 这里没有做分页，如果大于20篇文章。那么我们只取前20篇
        if (blogs.size() > 20) {
            model.addAttribute("blogs", blogs.subList(0, 20));
        }
        model.addAttribute("blogs", blogs);
        model.addAttribute("archiveRecords", archiveRecords);
        model.addAttribute("tags", tags);

        return "/journal";

    }


    /**
     * 根据文章Id查询出文章的具体信息，显示详情页
     *
     * @return
     */
    @GetMapping(value = "/blogs/:{blogId}")
    public String selectBlogsById(@PathVariable("blogId") Integer blogId, Model model) {


        Blog blog = blogService.findBlogDetailById(blogId);

        model.addAttribute("blog", blog);
        return "/journalDetail";

    }

    /**
     * 进入管理文章页面
     *
     * @param currentPage 默认是0
     * @return
     */
    @GetMapping(value = "/postlist/{currentPage}")
    public String postList(@PathVariable("currentPage") Integer currentPage, Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        //查询分页+条件+排序的数据
        Page<Blog> blogPage = blogService.paginateAndSort(currentPage - 1, user);


        //得到分页的数据，总页数，总记录数，当前页数
        List<Blog> blogsContent = blogPage.getContent();
        int totalPages = blogPage.getTotalPages();
        long totalElements = blogPage.getTotalElements();
        int returnCurrentPage = blogPage.getNumber() + 1;


        model.addAttribute("blogsContent", blogsContent);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("returnCurrentPage", returnCurrentPage);
        return "/manageJournal";

    }

    /**
     * 根据文章Id删除文章
     *
     * @param blogId
     * @return
     */
    @DeleteMapping(value = "/blogs/{blogId}")
    @ResponseBody
    public Result<Blog> deleteBlogById(@PathVariable("blogId") Integer blogId) {

        blogService.deleteBlogById(blogId);

        return ResultUtil.success();

    }


    /**
     * 编辑文章，跳转到编辑页面
     *
     * @param blogId
     * @return
     */
    @GetMapping(value = "/postedit/{blogId}")
    public String  toPostEditView(@PathVariable("blogId") Integer blogId, Model model) {

        Blog blog = blogService.findBlogDetailById(blogId);

        model.addAttribute("blog", blog);

        return "postedit";

    }


}
