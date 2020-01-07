package com.teeya.user.mapper;

import com.teeya.user.entity.pojo.UserEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * Created by Mybatis Generator 2019/11/21
 */
public interface UserEntityMapper extends BaseMapper<UserEntity> {

    UserEntity queryByUsername(@Param("username") String username);

    UserEntity selectByPhone(@Param("phone") String phone);
}