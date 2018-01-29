package com.zhongfucheng.controller;

import com.zhongfucheng.domain.Tag;
import com.zhongfucheng.domain.User;
import com.zhongfucheng.dto.Result;
import com.zhongfucheng.service.TagService;
import com.zhongfucheng.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 */

@Controller
public class TagController {

    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TagService tagService;


    /**
     * 查询当前用户的标签
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String  getTagsByUserId(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("labelStatistics", tagService.selectTagNamesAndCount(user));

        return "category";
    }

    /**
     * 根据标签名删除标签
     * @param tagName
     * @return
     */
    @DeleteMapping("/tags/{tagName}")
    @ResponseBody
    public Result<Tag> deleteTagByTagName(@PathVariable("tagName") String  tagName) {
        tagService.deleteTagByTagName(tagName);

        return ResultUtil.success();
    }

    /**
     * 更新标签名
     *
     * @param oldVal 旧的标签名
     * @param newVal 新的标签名
     * @return
     */
    @PutMapping("/tags/{oldVal}/{newVal}")
    @ResponseBody
    public Result<Tag> updateTagByTagName(@PathVariable("oldVal") String oldVal, @PathVariable("newVal") String newVal) {

        tagService.updateTag(oldVal, newVal);

        return ResultUtil.success();
    }




}
