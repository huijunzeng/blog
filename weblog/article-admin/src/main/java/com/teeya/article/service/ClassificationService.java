package com.teeya.article.service;

import com.teeya.article.entity.pojo.ClassificationEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.article.entity.vo.ClassificationForm;

/**
 * <p>
 * 文章分类表 服务类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
public interface ClassificationService extends IService<ClassificationEntity> {

    /**
     * 新增文章分类
     * @param classificationForm
     * @return
     */
    int insert(ClassificationForm classificationForm);
}
