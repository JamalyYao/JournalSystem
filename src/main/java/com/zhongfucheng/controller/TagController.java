package com.zhongfucheng.controller;

import com.zhongfucheng.domain.Tag;
import com.zhongfucheng.service.BlogService;
import com.zhongfucheng.service.TagService;
import com.zhongfucheng.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public List<Tag> getTagsByUserId() {

        return null;
    }
}
