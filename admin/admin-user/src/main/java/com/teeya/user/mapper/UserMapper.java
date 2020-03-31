package com.teeya.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teeya.user.entity.pojo.UserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Mybatis Generator 2019/11/21
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    UserEntity queryByUsername(@Param("username") String username);

    UserEntity queryByPhone(@Param("phone") String phone);
}