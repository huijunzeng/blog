package com.teeya.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teeya.article.entity.param.WebArticleQueryParam;
import com.teeya.article.entity.pojo.ArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 博客文章表 Mapper 接口
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
public interface ArticleMapper extends BaseMapper<ArticleEntity> {

    IPage<ArticleEntity> queryList(Page<ArticleEntity> page, WebArticleQueryParam webArticleQueryParam);
}
