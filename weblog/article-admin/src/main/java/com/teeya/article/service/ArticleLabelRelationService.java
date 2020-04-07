package com.teeya.article.service;

import com.teeya.article.entity.pojo.ArticleLabelRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 文章和标签关系表 服务类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
public interface ArticleLabelRelationService extends IService<ArticleLabelRelationEntity> {

    /**
     * 给文章添加标签
     * @param articleId
     * @param labelIds
     * @return
     */
    boolean saveBatch(String articleId, Set<String> labelIds);

    /**
     * 根据文章id删除文章的标签
     * @param articleId
     */
    boolean removeByArticleId(String articleId);
}
