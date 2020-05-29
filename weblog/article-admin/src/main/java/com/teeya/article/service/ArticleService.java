package com.teeya.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.article.entity.form.ArticleQueryForm;
import com.teeya.article.entity.form.ArticleUpdateForm;
import com.teeya.article.entity.param.WebArticleQueryParam;
import com.teeya.article.entity.pojo.ArticleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.article.entity.form.ArticleSaveForm;

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
     * @param articleSaveForm
     */
    boolean save(ArticleSaveForm articleSaveForm);

    /**
     * 修改文章
     * @param id
     * @param articleUpdateForm
     * @return
     */
    boolean update(Long id, ArticleUpdateForm articleUpdateForm);

    /**
     * 根据文章id获取指定文章信息
     * @param id
     * @return
     */
    ArticleEntity get(Long id);

    /**
     * 根据条件获取文章信息列表
     * @param articleQueryForm
     * @return
     */
    IPage queryList(ArticleQueryForm articleQueryForm);

    /**
     * 根据id删除文章
     * @param id
     * @return
     */
    boolean remove(Long id);

    /**
     * 前台页面根据条件获取文章信息列表
     * @param webArticleQueryParam
     * @return
     */
    IPage queryWebList(WebArticleQueryParam webArticleQueryParam);
}
