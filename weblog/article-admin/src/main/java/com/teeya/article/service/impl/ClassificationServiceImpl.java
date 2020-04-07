package com.teeya.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teeya.article.entity.form.ClassificationQueryForm;
import com.teeya.article.entity.form.ClassificationUpdateForm;
import com.teeya.article.entity.pojo.*;
import com.teeya.article.entity.form.ClassificationForm;
import com.teeya.article.mapper.ClassificationMapper;
import com.teeya.article.service.ArticleClassificationRelationService;
import com.teeya.article.service.ClassificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private ArticleClassificationRelationService articleClassificationRelationService;

    @Override
    public boolean insert(ClassificationForm classificationForm) {
        ClassificationEntity classificationEntity = BeanUtils.instantiateClass(ClassificationEntity.class);
        BeanUtils.copyProperties(classificationForm, classificationEntity);
        return super.save(classificationEntity);
    }

    @Override
    public boolean update(String id, ClassificationUpdateForm classificationUpdateForm) {
        ClassificationEntity classificationEntity = super.getById(id);
        BeanUtils.copyProperties(classificationUpdateForm, classificationEntity);
        return super.updateById(classificationEntity);
    }

    @Override
    public ClassificationEntity get(String id) {
        return super.getById(id);
    }

    @Override
    public List<ClassificationEntity> queryListByArticleId(String articleId) {
        QueryWrapper<ArticleClassificationRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ArticleClassificationRelationEntity::getArticleId, articleId);
        List<ArticleClassificationRelationEntity> articleClassificationRelationEntities = articleClassificationRelationService.list(queryWrapper);
        Set<String> labelIds = articleClassificationRelationEntities.stream().map(articleClassificationRelationEntity -> articleClassificationRelationEntity.getClassificationId()).collect(Collectors.toSet());
        return super.listByIds(labelIds);
    }

    @Override
    public IPage queryList(ClassificationQueryForm classificationQueryForm) {
        Page page = classificationQueryForm.getPage();
        LambdaQueryWrapper<ClassificationEntity> queryWrapper = classificationQueryForm.build().lambda();
        queryWrapper.eq(StringUtils.isNotBlank(classificationQueryForm.getClassificationName()), ClassificationEntity::getClassificationName, classificationQueryForm.getClassificationName());
        queryWrapper.eq(ClassificationEntity::getDeleted, 0);
        queryWrapper.orderByDesc(ClassificationEntity::getCreatedTime);
        IPage<ClassificationEntity> iPageUser = super.page(page, queryWrapper);
        return iPageUser;
    }

    @Override
    public List<ClassificationEntity> getAll() {
        QueryWrapper<ClassificationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ClassificationEntity::getDeleted, 0).orderByDesc(ClassificationEntity::getUpdatedTime);
        return super.list(queryWrapper);
    }
}
