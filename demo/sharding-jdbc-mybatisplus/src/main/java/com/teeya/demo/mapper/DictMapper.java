package com.teeya.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teeya.demo.entity.pojo.DictEntity;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
public interface DictMapper extends BaseMapper<DictEntity> {

    boolean createTableIfNotExists();
}
