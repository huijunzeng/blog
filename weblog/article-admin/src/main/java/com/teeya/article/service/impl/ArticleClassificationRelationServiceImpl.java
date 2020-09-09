package com.teeya.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teeya.article.entity.pojo.ArticleClassificationRelationEntity;
import com.teeya.article.mapper.ArticleClassificationRelationMapper;
import com.teeya.article.service.ArticleClassificationRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章和分类关系表 服务实现类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Service
@Slf4j
public class ArticleClassificationRelationServiceImpl extends ServiceImpl<ArticleClassificationRelationMapper, ArticleClassificationRelationEntity> implements ArticleClassificationRelationService {

    @Override
    public boolean saveBatch(Long articleId, Set<Long> classificationIds) {
        if (classificationIds.isEmpty()){
            return false;
        }
        this.removeByArticleId(articleId);
        Set<ArticleClassificationRelationEntity> articleClassificationRelationEntities = classificationIds.stream().map(classificationId -> new ArticleClassificationRelationEntity(articleId, classificationId)).collect(Collectors.toSet());
        return super.saveBatch(articleClassificationRelationEntities);
    }

    @Override
    public boolean removeByArticleId(Long articleId) {
        QueryWrapper<ArticleClassificationRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ArticleClassificationRelationEntity::getArticleId, articleId).eq(ArticleClassificationRelationEntity::getDeleted, 0);
        return super.remove(queryWrapper);
    }
}
