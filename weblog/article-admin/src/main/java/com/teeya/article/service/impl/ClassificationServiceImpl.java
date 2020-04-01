package com.teeya.article.service.impl;

import com.teeya.article.entity.pojo.ClassificationEntity;
import com.teeya.article.entity.form.ClassificationForm;
import com.teeya.article.mapper.ClassificationMapper;
import com.teeya.article.service.ClassificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章分类表 服务实现类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Service
@Slf4j
public class ClassificationServiceImpl extends ServiceImpl<ClassificationMapper, ClassificationEntity> implements ClassificationService {

    @Autowired
    private ClassificationMapper classificationMapper;

    @Override
    public int insert(ClassificationForm classificationForm) {
        ClassificationEntity classificationEntity = BeanUtils.instantiateClass(ClassificationEntity.class);
        BeanUtils.copyProperties(classificationForm, classificationEntity);
        return classificationMapper.insert(classificationEntity);
    }
}
