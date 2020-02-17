package com.teeya.authorization.service;

import com.teeya.common.entity.vo.Result;
import com.teeya.user.entity.pojo.RoleEntity;

import java.util.List;

public interface RoleService {

    /**
     * 根据用户id获取相应的角色集合
     * @param userId
     * @return
     */
    Result queryListByUserId(String userId);
}
