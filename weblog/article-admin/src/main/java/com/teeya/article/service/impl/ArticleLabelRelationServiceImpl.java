package com.teeya.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teeya.article.entity.pojo.ArticleLabelRelationEntity;
import com.teeya.article.mapper.ArticleLabelRelationMapper;
import com.teeya.article.service.ArticleLabelRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章和标签关系表 服务实现类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Service
@Slf4j
public class ArticleLabelRelationServiceImpl extends ServiceImpl<ArticleLabelRelationMapper, ArticleLabelRelationEntity> implements ArticleLabelRelationService {

    @Override
    public boolean saveBatch(String articleId, Set<String> labelIds) {
        if (labelIds.isEmpty()){
            return false;
        }
        this.removeByArticleId(articleId);
        Set<ArticleLabelRelationEntity> articleLabelRelationEntities = labelIds.stream().map(labelId -> new ArticleLabelRelationEntity(articleId, labelId)).collect(Collectors.toSet());
        return super.saveBatch(articleLabelRelationEntities);
    }

    @Override
    public boolean removeByArticleId(String articleId) {
        QueryWrapper<ArticleLabelRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ArticleLabelRelationEntity::getArticleId, articleId).eq(ArticleLabelRelationEntity::getDeleted, 0);
        return super.remove(queryWrapper);
    }
}
