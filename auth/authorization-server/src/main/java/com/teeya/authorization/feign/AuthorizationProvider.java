package com.teeya.authorization.feign;

import com.teeya.authorization.entity.UserEntity;
import com.teeya.authorization.entity.RoleEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *调取user用户服务，在授权的时候提供查询用户接口以及查询角色接口
 *
 * fallback类型时 对应回调应该是实现BizOrderAccpetAPI接口 ，重写方法
 * fallbackFactory类型时 对应回调应该是实现FallbackFactory<AuthorizationProvider>接口,重写方法
 */

@Component
@FeignClient(name = "admin-user", fallbackFactory = AuthorizationProviderFallback.class)
public interface AuthorizationProvider {

    /**
     * 必须匹配admin-user服务的完整/user/selectByUsername路径
     * 响应数据的字段不必一一相同
     * @param uniqueId
     * @return
     */
    @GetMapping("/user")
    UserEntity getByUniqueId(@RequestParam(value = "uniqueId") String uniqueId);

    @GetMapping("/role/user/{username}")
    List<RoleEntity> queryListByUsername(@PathVariable(value = "username") String username);
}
