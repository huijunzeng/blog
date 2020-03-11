package com.teeya.user.interceptor;

import com.teeya.common.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息拦截器
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    /**
     * 请求头变量key
     */
    private static final String AUTHORIZATION = "authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从网关获取并校验,通过校验就可信任x-client-token-user中的信息
        String userInfoString = StringUtils.defaultIfBlank(request.getHeader(AUTHORIZATION), null);
        UserContextHolder.getInstance().setContext(userInfoString);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContextHolder.getInstance().clear();
    }
}
