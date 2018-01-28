package com.zhongfucheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by ozc on 2018/1/28.
 *
 * @author ozc
 * @version 1.0
 */


@Controller
public class ViewController {


    /**
     * 跳转到相关的页面
     *
     * @return
     */
    @GetMapping("/view/{url}")
    public String goURL(@PathVariable("url") String url) {

        return "/" + url;
    }


}
