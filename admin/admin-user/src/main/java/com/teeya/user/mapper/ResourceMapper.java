package com.teeya.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teeya.user.entity.pojo.ResourceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by Mybatis Generator 2019/11/21
 */
public interface ResourceMapper extends BaseMapper<ResourceEntity> {

    List<ResourceEntity> queryListByResourceIds(@Param("resourceIds") Set<String> resourceIds);
}