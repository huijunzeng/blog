package com.teeya.article.config;

import com.teeya.common.web.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义web配置
 * @Author: ZJH
 * @Date: 2019/12/5 10:15
 */
@Configuration
public class WebServerMvcConfigurerAdapter implements WebMvcConfigurer {

    /**
     * 用户信息拦截器
     * @return
     */
    @Bean
    public HandlerInterceptor userInterceptor() {
        return new UserInterceptor();
    }

    /**
     * 注入拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor());
    }
}
