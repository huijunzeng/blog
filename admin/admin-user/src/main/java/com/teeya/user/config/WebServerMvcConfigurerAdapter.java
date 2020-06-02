package com.teeya.user.config;

import com.teeya.common.web.interceptor.UserInterceptor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Configuration
public class WebServerMvcConfigurerAdapter implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor userInterceptor() {
        return new UserInterceptor();
    }

    /**
     * 注入用户信息拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor());
    }
}
