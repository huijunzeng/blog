package com.teeya.article.service;

import com.teeya.article.entity.pojo.ArticleClassificationRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 文章和分类关系表 服务类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
public interface ArticleClassificationRelationService extends IService<ArticleClassificationRelationEntity> {

    /**
     * 给文章添加分类
     * @param articleId
     * @param classificationIds
     * @return
     */
    boolean saveBatch(Long articleId, Set<Long> classificationIds);

    /**
     * 根据文章id删除文章的分类
     * @param articleId
     */
    boolean removeByArticleId(Long articleId);
}
