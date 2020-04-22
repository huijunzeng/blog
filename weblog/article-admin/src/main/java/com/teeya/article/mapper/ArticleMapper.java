package com.teeya.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teeya.article.entity.form.ArticleQueryForm;
import com.teeya.article.entity.param.WebArticleQueryParam;
import com.teeya.article.entity.pojo.ArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 博客文章表 Mapper 接口
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
public interface ArticleMapper extends BaseMapper<ArticleEntity> {

    IPage<ArticleEntity> queryList(@Param("page") Page<ArticleEntity> page, @Param("articleQueryForm") ArticleQueryForm articleQueryForm);

    IPage<ArticleEntity> queryWebList(@Param("page") Page<ArticleEntity> page, @Param("webArticleQueryParam") WebArticleQueryParam webArticleQueryParam);
}
