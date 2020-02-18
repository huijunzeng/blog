package com.teeya.client.provider;

import com.teeya.common.entity.vo.Result;
import com.teeya.user.entity.pojo.ResourceEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * 调取资源服务器的接口，进行鉴权
 * @Author: ZJH
 * @Date: 2020/1/16 14:18
 */

@Component
@FeignClient(name = "authentication-server", fallback = AuthProviderFallback.class)
public interface AuthProvider {

    /**
     * 必须匹配authentication-server服务的完整/user/selectByUsername路径
     */
    @GetMapping("/resource/all")
    List<ResourceEntity> queryAll();

}
