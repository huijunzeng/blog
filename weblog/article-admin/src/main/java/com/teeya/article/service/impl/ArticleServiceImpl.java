package com.teeya.article.service.impl;

import com.teeya.article.entity.pojo.ArticleEntity;
import com.teeya.article.entity.form.ArticleForm;
import com.teeya.article.mapper.ArticleMapper;
import com.teeya.article.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public void insert(ArticleForm articleForm) {
        ArticleEntity articleEntity = BeanUtils.instantiateClass(ArticleEntity.class);
        BeanUtils.copyProperties(articleForm, articleEntity);
        log.info("insert_userEntity=======: " + articleEntity.toString());
        articleMapper.insert(articleEntity);
    }
}
