package com.teeya.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teeya.user.entity.pojo.UserRoleRelationEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Mybatis Generator 2019/11/21
 */
public interface UserRoleRelationEntityMapper extends BaseMapper<UserRoleRelationEntity> {

    List<UserRoleRelationEntity> queryListByUserId(@Param("userId") String userId);
}