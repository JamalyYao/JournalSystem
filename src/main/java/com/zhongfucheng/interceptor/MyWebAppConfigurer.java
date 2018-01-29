package com.zhongfucheng.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ozc on 2018/1/18.
 *
 * @author ozc
 * @version 1.0
 */
@Configuration
public class MyWebAppConfigurer
        extends WebMvcConfigurerAdapter {


    /**
     * 为了在拦截器上注入Bean不为null
     *
     * @return
     */

    @Bean
    UserInterceptor userInterceptor() {
        return new UserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(userInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html").excludePathPatterns("/register.html").excludePathPatterns("/login.html").excludePathPatterns("/user/**");

        super.addInterceptors(registry);
    }

}
