package com.teeya.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teeya.article.entity.form.ArticleQueryForm;
import com.teeya.article.entity.form.ArticleUpdateForm;
import com.teeya.article.entity.pojo.ArticleEntity;
import com.teeya.article.entity.form.ArticleForm;
import com.teeya.article.entity.pojo.ArticleLabelRelationEntity;
import com.teeya.article.entity.pojo.LabelEntity;
import com.teeya.article.mapper.ArticleMapper;
import com.teeya.article.service.ArticleService;
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

    @Override
    public void insert(ArticleForm articleForm) {
        ArticleEntity articleEntity = BeanUtils.instantiateClass(ArticleEntity.class);
        BeanUtils.copyProperties(articleForm, articleEntity);
        log.info("insert_articleEntity=======: " + articleEntity.toString());
        articleMapper.insert(articleEntity);
    }

    @Override
    public boolean update(String id, ArticleUpdateForm articleUpdateForm) {
        ArticleEntity articleEntity = super.getById(id);
        BeanUtils.copyProperties(articleUpdateForm, articleEntity);
        return super.updateById(articleEntity);
    }

    @Override
    public ArticleEntity get(String id) {
        return super.getById(id);
    }

    @Override
    public IPage queryList(ArticleQueryForm articleQueryForm) {
        Page page = articleQueryForm.getPage();
        LambdaQueryWrapper<ArticleEntity> queryWrapper = articleQueryForm.build().lambda();
        queryWrapper.eq(StringUtils.isNotBlank(articleQueryForm.getTitle()), ArticleEntity::getTitle, articleQueryForm.getTitle());
        queryWrapper.orderByDesc(ArticleEntity::getTitle);
        IPage<ArticleEntity> iPageUser = super.page(page, queryWrapper);
        return iPageUser;
    }
}
