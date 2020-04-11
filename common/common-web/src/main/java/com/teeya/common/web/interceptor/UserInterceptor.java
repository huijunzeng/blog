package com.teeya.common.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teeya.common.web.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户信息拦截器
 * @Author: ZJH
 * @Date: 2020/3/1 12:18
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    /**
     * 请求头变量key
     */
    private static final String AUTHORIZATION = "authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取authorization信息（包含jwt中body体的信息）
        String userInfoString = StringUtils.defaultIfBlank(request.getHeader(AUTHORIZATION), "{}");
        // 将body体的信息转成map类型
        UserContextHolder.getInstance().setContext(new ObjectMapper().readValue(userInfoString, Map.class));
        return true;
    }

    /**
     * 执行完之后必须手动清除，不然可能会造成栈溢出
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContextHolder.getInstance().clear();
    }
}