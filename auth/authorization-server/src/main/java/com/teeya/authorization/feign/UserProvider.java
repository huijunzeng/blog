package com.teeya.authorization.feign;

import com.teeya.user.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "admin-user")
public interface UserProvider {

    /**
     * 必须匹配admin-user服务的完整/user/selectByUsername路径
     * @param username
     * @return
     */
    @GetMapping("/user/selectByUsername")
    UserEntity selectByUsername(@RequestParam(value = "username") String username);

    @GetMapping("/user/loadUserByPhone")
    UserEntity loadUserByPhone(@RequestParam(value = "phone") String phone);
}
