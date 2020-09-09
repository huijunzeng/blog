package com.teeya.demo.service.impl;

import com.teeya.demo.entity.form.PointSaveForm;
import com.teeya.demo.entity.pojo.PointsEntity;
import com.teeya.demo.mapper.PointsMapper;
import com.teeya.demo.service.PointsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZJH
 * @since 2020-05-29
 */
@Service
public class PointsServiceImpl extends ServiceImpl<PointsMapper, PointsEntity> implements PointsService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(PointSaveForm pointSaveForm) {
        PointsEntity pointsEntity = BeanUtils.instantiateClass(PointsEntity.class);
        BeanUtils.copyProperties(pointSaveForm, pointsEntity);
        return super.save(pointsEntity);
    }
}
