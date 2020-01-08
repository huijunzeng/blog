package com.teeya.user.mapper;

import com.teeya.user.entity.pojo.RoleResourceRelationEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Set;

/**
 * Created by Mybatis Generator 2019/11/21
 */
public interface RoleResourceRelationEntityMapper extends BaseMapper<RoleResourceRelationEntity> {

    List<RoleResourceRelationEntity> queryListByRoleIds(@Param("roleIds") Set<String> roleIds);
}