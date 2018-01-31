package com.zhongfucheng.controller;

import com.zhongfucheng.domain.Journal;
import com.zhongfucheng.domain.Tag;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.ArchiveRecords;
import com.zhongfucheng.dto.Result;
import com.zhongfucheng.enums.ResultEnum;
import com.zhongfucheng.exception.UserException;
import com.zhongfucheng.service.JournalService;
import com.zhongfucheng.service.TagService;
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
public class JournalController {

    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JournalService journalService;
    @Autowired
    private TagService tagService;


    /**
     * 发表日记(保存日志)
     *
     * @param session
     * @return
     */
    @PostMapping(value = "/journal", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result saveJournal(HttpSession session, Journal journal, String tags) {


        //得到当前用户信息
        User user = (User) session.getAttribute("user");

        //设置Blog的相关数据，保存Blog
        journal.setAuthor(user.getUserNickName());
        journal.setCreateTime(new Date());
        journal.setUser(user);

        Journal saveJournal = journalService.saveJournal(journal);


        //解析页面带过来的Tags，保存Tag
        if (tags != null && tags.length() > 0) {
            String[] eachTag = tags.split(",");
            List<Tag> tagList = new ArrayList<>();
            for (String tag : eachTag) {
                Tag tag1 = new Tag();
                tag1.setUser(user);
                tag1.setTagName(tag);
                tag1.setJournal(saveJournal);
                tagList.add(tag1);
            }
            tagService.saveTags(tagList);
        }

        return ResultUtil.success(user);

    }


    /**
     * 更新文章(更新日志的内容)
     *
     * @param journal
     * @param tags
     * @return
     */
    @PutMapping(value = "/journal", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result updateJournal(Journal journal, String tags, HttpSession session) {

        User user = (User) session.getAttribute("user");


        //根据页面带过来的Id获取原有的信息，并置换页面传递过来的数据
        Journal updateJournal = journalService.findJournalDetailById(journal.getJournalId());
        updateJournal.setTitle(journal.getTitle());
        updateJournal.setContentNoHTML(journal.getContentNoHTML());
        updateJournal.setContent(journal.getContent());


        //删除该文章原有的标签
        tagService.deleteTagByBlog(updateJournal);
        updateJournal.getTagList().clear();


        List<Tag> tagList = new ArrayList<>();

        //解析页面带过来的Tags，保存Tag
        if (tags != null && tags.length() > 0) {
            String[] eachTag = tags.split(",");
            for (String tag : eachTag) {
                Tag tag1 = new Tag();
                tag1.setUser(user);
                tag1.setTagName(tag);
                tag1.setJournal(updateJournal);
                tagList.add(tag1);
            }
        }

        //设置Blog的相关数据，保存Blog
        tagService.saveTags(tagList);
        journalService.updateJournalContent(updateJournal);

        return ResultUtil.success();

    }


    /**
     * 查询用户下所有日志以及相关信息
     *
     * @return
     */
    @GetMapping(value = "/journals")
    public String selectJournals(HttpSession session, Model model) {

        List<Journal> journals;
        List<ArchiveRecords> archiveRecords;
        List<String> tags;

        //查询博客相关信息
        User user = (User) session.getAttribute("user");

        if (user != null) {
            journals = journalService.selectAllJournals(user);

            //查询文章存档信息
            archiveRecords = journalService.selectArchiveRecords(String.valueOf(user.getUserId()));

            //查询标签
            tags = tagService.selectTagNames(user);

            // 这里没有做分页，如果大于20篇文章。那么我们只取前20篇
            if (journals.size() > 20) {
                model.addAttribute("journals", journals.subList(0, 20));
            }
            model.addAttribute("journals", journals);
            model.addAttribute("archiveRecords", archiveRecords);
            model.addAttribute("tags", tags);
        }


        return "/journal";

    }


    /**
     * 根据标签查询用户下博客
     *
     * @return
     */
    @GetMapping(value = "/journals/{tag}")
    public String selectJournalsByTag(@PathVariable("tag") String tag, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        List<Journal> journals = null;
        List<ArchiveRecords> archiveRecords;
        List<String> tags;


        //根据标签名查询出对应的文章
        if (tag != null) {
            journals = journalService.selectJournalsByTag(tag);
        }

        //查询文章存档信息
        archiveRecords = journalService.selectArchiveRecords(String.valueOf(user.getUserId()));


        //查询标签
        tags = tagService.selectTagNames(user);

        // 这里没有做分页，如果大于20篇文章。那么我们只取前20篇
        if (journals.size() > 20) {
            model.addAttribute("journals", journals.subList(0, 20));
        }
        model.addAttribute("journals", journals);
        model.addAttribute("archiveRecords", archiveRecords);
        model.addAttribute("tags", tags);

        return "/journal";

    }


    /**
     * 根据时间查询用户下博客
     *
     * @return
     */
    @GetMapping(value = "/journals/{year}/{month}")
    public String selectJournalsByDate(@PathVariable("year") Integer year, @PathVariable("month") Integer month, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        List<Journal> journals;
        List<ArchiveRecords> archiveRecords;
        List<String> tags;


        //解析日期，根据时间查询相关文章
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
            Date startDate = sdf.parse(year + "-" + month + "-1");
            Date endDate = sdf.parse(year + "-" + month + "-32");
            journals = journalService.selectJournalsByTime(user, startDate, endDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new UserException(ResultEnum.UNKONW_ERROR);
        }
        //查询文章存档信息
        archiveRecords = journalService.selectArchiveRecords(String.valueOf(user.getUserId()));


        //查询标签
        tags = tagService.selectTagNames(user);

        // 这里没有做分页，如果大于20篇文章。那么我们只取前20篇
        if (journals.size() > 20) {
            model.addAttribute("journals", journals.subList(0, 20));
        }
        model.addAttribute("journals", journals);
        model.addAttribute("archiveRecords", archiveRecords);
        model.addAttribute("tags", tags);

        return "/journal";

    }


    /**
     * 根据文章Id查询出文章的具体信息，显示详情页
     *
     * @return
     */
    @GetMapping(value = "/journals/:{journalId}")
    public String selectJournalsById(@PathVariable("journalId") Integer journalId, Model model) {


        Journal journal = journalService.findJournalDetailById(journalId);

        model.addAttribute("journal", journal);
        return "/journalDetail";

    }

    /**
     * 进入管理文章页面
     *
     * @param currentPage 默认是0
     * @return
     */
    @GetMapping(value = "/postList/{currentPage}")
    public String postList(@PathVariable("currentPage") Integer currentPage, Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        //查询分页+条件+排序的数据
        Page<Journal> blogPage = journalService.paginateAndSort(currentPage - 1, user);


        //得到分页的数据，总页数，总记录数，当前页数
        List<Journal> blogsContent = blogPage.getContent();
        int totalPages = blogPage.getTotalPages();
        long totalElements = blogPage.getTotalElements();
        int returnCurrentPage = blogPage.getNumber() + 1;


        model.addAttribute("blogsContent", blogsContent);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("returnCurrentPage", returnCurrentPage);
        return "postList";

    }

    /**
     * 根据文章Id删除文章
     *
     * @param journalId
     * @return
     */
    @DeleteMapping(value = "/journal/{journalId}")
    @ResponseBody
    public Result<Journal> deleteBlogById(@PathVariable("journalId") Integer journalId) {

        journalService.deleteJournalById(journalId);

        return ResultUtil.success();

    }


    /**
     * 编辑文章，跳转到编辑页面
     *
     * @param journalId
     * @return
     */
    @GetMapping(value = "/postEdit/{journalId}")
    public String toPostEditView(@PathVariable("journalId") Integer journalId, Model model) {

        Journal journal = journalService.findJournalDetailById(journalId);

        model.addAttribute("journal", journal);

        return "/postEdit";
    }

}
