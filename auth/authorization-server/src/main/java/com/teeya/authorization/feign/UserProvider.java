package com.teeya.authorization.feign;

import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 *调取user用户服务，在授权的时候提供查询用户接口以及查询角色接口
 */
@FeignClient(name = "admin-user", fallbackFactory = UserProviderFallback.class)
public interface UserProvider {

    /**
     * 必须匹配admin-user服务的完整/user/selectByUsername路径
     * @param username
     * @return
     */
    @GetMapping("/user")
    UserEntity queryByUsername(@RequestParam(value = "username") String username);

    @GetMapping("/user/loadUserByPhone")
    UserEntity loadUserByPhone(@RequestParam(value = "phone") String phone);

    @GetMapping("/role/user/{userId}")
    List<RoleEntity> queryListByUserId(@PathVariable(value = "userId") String userId);
}
