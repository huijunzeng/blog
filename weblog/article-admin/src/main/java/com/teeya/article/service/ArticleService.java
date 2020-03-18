package com.teeya.article.service;

import com.teeya.article.entity.pojo.ArticleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.article.entity.vo.ArticleForm;

/**
 * <p>
 * 博客文章表 服务类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
public interface ArticleService extends IService<ArticleEntity> {

    /**
     * 新增文章
     * @param articleForm
     */
    void insert(ArticleForm articleForm);
}
