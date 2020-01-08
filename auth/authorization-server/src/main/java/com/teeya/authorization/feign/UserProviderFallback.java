package com.teeya.authorization.feign;

import com.teeya.user.entity.pojo.RoleEntity;
import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.entity.vo.UserVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Author: ZJH
 * @Date: 2020/1/8 9:48
 */

@Component
public class UserProviderFallback implements UserProvider {

    @Override
    public UserEntity queryByUsername(String username) {
        System.out.println("=================触发降级");
        return null;
    }

    @Override
    public UserEntity loadUserByPhone(String phone) {
        return null;
    }

    @Override
    public List<RoleEntity> queryListByUserId(String userId) {
        System.out.println("=================触发降级");
        return null;
    }
}
