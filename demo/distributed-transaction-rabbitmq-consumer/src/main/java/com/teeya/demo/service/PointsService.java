package com.teeya.demo.service;

import com.teeya.demo.entity.form.PointSaveForm;
import com.teeya.demo.entity.pojo.PointsEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZJH
 * @since 2020-05-21
 */
public interface PointsService extends IService<PointsEntity> {

    boolean save(PointSaveForm pointSaveForm);
}
