package com.teeya.user.service;

import com.teeya.user.entity.pojo.UserEntity;
import com.teeya.user.entity.vo.UserVo;

public interface UserService {

    UserVo queryByUsername(String username);

    UserEntity selectByPhone(String phone);
}
