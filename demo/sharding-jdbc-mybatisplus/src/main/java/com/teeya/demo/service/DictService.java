package com.teeya.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.demo.entity.pojo.DictEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
public interface DictService extends IService<DictEntity> {

    /**
     * 创建数据库
     * @return
     */
    boolean createTable();

    boolean save();

    DictEntity get(Long id);
}
