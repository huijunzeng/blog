package com.teeya.authorization.feign;

import com.teeya.user.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "admin-user")
public interface UserService {

    /**
     * 必须匹配admin-user服务的完整/user/selectByUserName路径
     * @param userName
     * @return
     */
    @GetMapping("/user/selectByUserName")
    UserEntity selectByUserName(@RequestParam(value = "userName") String userName);
}
