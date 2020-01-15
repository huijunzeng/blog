package com.teeya.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teeya.user.entity.pojo.RoleResourceRelationEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by Mybatis Generator 2019/11/21
 */
public interface RoleResourceRelationEntityMapper extends BaseMapper<RoleResourceRelationEntity> {

    List<RoleResourceRelationEntity> queryListByRoleIds(@Param("roleIds") List<String> roleIds);
}