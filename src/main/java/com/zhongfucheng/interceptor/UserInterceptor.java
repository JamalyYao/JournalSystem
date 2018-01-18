package com.zhongfucheng.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 *
 * 除了index.html,login.html,register.html。其他的链接都需要登陆状态下
 */
public class UserInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {


        //获取请求的超链接
        String requestURI = request.getRequestURI();

        System.out.println(requestURI);



        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
