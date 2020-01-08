package com.teeya.user.mapper;

import com.teeya.user.entity.pojo.ResourceEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Set;

/**
 * Created by Mybatis Generator 2019/11/21
 */
public interface ResourceEntityMapper extends BaseMapper<ResourceEntity> {

    List<ResourceEntity> queryListByResourceIds(@Param("resourceIds") Set<String> resourceIds);
}