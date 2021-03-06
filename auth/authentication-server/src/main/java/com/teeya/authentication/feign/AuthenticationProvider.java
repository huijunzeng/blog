package com.teeya.authentication.feign;

import com.teeya.authentication.entity.ResourceEntity;
import com.teeya.common.web.interceptor.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *  调取user用户服务，在认证的时候校验用户访问资源的合法性
 *  fallback类型时 对应回调应该是实现BizOrderAccpetAPI接口 ，重写方法
 *  fallbackFactory类型时 对应回调应该是实现FallbackFactory<UserProvider>接口,重写方法
 */

@Component
@FeignClient(name = "admin-user", configuration = FeignRequestInterceptor.class, fallback = AuthenticationProviderFallback.class)
public interface AuthenticationProvider {

    /**
     * 必须匹配admin-user服务的完整/user/selectByUsername路径
     */
    @GetMapping("/resource/all")
    List<ResourceEntity> getAll();

    @GetMapping("/resource/user/{username}")
    List<ResourceEntity> queryListByUsername(@PathVariable(value = "username") String username);
}
