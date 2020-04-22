package com.teeya.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teeya.article.entity.form.ArticleQueryForm;
import com.teeya.article.entity.form.ArticleUpdateForm;
import com.teeya.article.entity.param.WebArticleQueryParam;
import com.teeya.article.entity.pojo.ArticleEntity;
import com.teeya.article.entity.form.ArticleSaveForm;
import com.teeya.article.mapper.ArticleMapper;
import com.teeya.article.service.ArticleClassificationRelationService;
import com.teeya.article.service.ArticleLabelRelationService;
import com.teeya.article.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客文章表 服务实现类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleLabelRelationService articleLabelRelationService;
    @Autowired
    private ArticleClassificationRelationService articleClassificationRelationService;

    @Override
    public boolean save(ArticleSaveForm articleSaveForm) {
        ArticleEntity articleEntity = BeanUtils.instantiateClass(ArticleEntity.class);
        BeanUtils.copyProperties(articleSaveForm, articleEntity);
        super.save(articleEntity);
        log.info("insert_articleEntity=======: " + articleEntity.toString());
        articleLabelRelationService.saveBatch(articleEntity.getId(), articleSaveForm.getLabelIds());
        return articleClassificationRelationService.saveBatch(articleEntity.getId(), articleSaveForm.getClassificationIds());
    }

    @Override
    public boolean update(String id, ArticleUpdateForm articleUpdateForm) {
        ArticleEntity articleEntity = super.getById(id);
        BeanUtils.copyProperties(articleUpdateForm, articleEntity);
        articleLabelRelationService.removeByArticleId(id);
        articleClassificationRelationService.removeByArticleId(id);
        super.updateById(articleEntity);
        articleLabelRelationService.saveBatch(articleEntity.getId(), articleUpdateForm.getLabelIds());
        return articleClassificationRelationService.saveBatch(articleEntity.getId(), articleUpdateForm.getClassificationIds());
    }

    @Override
    public ArticleEntity get(String id) {
        return super.getById(id);
    }

    @Override
    public IPage queryList(ArticleQueryForm articleQueryForm) {
        Page page = articleQueryForm.getPage();
        /*LambdaQueryWrapper<ArticleEntity> queryWrapper = articleQueryForm.build().lambda();
        queryWrapper.like(StringUtils.isNotBlank(articleQueryForm.getTitle()), ArticleEntity::getTitle, articleQueryForm.getTitle());
        queryWrapper.eq(null != articleQueryForm.getVisible() && !articleQueryForm.getVisible().equals(""), ArticleEntity::getVisible, articleQueryForm.getVisible());
        queryWrapper.eq(null != articleQueryForm.getDeleted() && !articleQueryForm.getDeleted().equals(""), ArticleEntity::getDeleted, articleQueryForm.getDeleted());
        queryWrapper.orderByDesc(ArticleEntity::getCreatedTime);
        IPage<ArticleEntity> iPageUser = super.page(page, queryWrapper);
        return iPageUser;*/
        return articleMapper.queryList(page, articleQueryForm);
    }

    @Override
    public boolean remove(String id) {
        return super.removeById(id);
    }

    @Override
    public IPage queryList(WebArticleQueryParam webArticleQueryParam) {
        Page page = webArticleQueryParam.getPage();
        return articleMapper.queryWebList(page, webArticleQueryParam);
    }
}
