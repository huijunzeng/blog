package com.teeya.authentication.feign;

import com.teeya.user.entity.pojo.ResourceEntity;
import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 调取user用户服务，在认证的时候校验用户访问资源的合法性
 */
@FeignClient(name = "admin-user", fallbackFactory = UserProviderFallback.class)
public interface UserProvider {

    /**
     * 必须匹配admin-user服务的完整/user/selectByUsername路径
     * @param username
     * @return
     */
    @GetMapping("/resource/user/{username}")
    List<ResourceEntity> queryListByUsername(@PathVariable("username") String username);
}
