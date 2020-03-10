package com.teeya.article.service.impl;

import com.teeya.article.entity.pojo.ArticleClassificationRelationEntity;
import com.teeya.article.mapper.ArticleClassificationRelationMapper;
import com.teeya.article.service.ArticleClassificationRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章和分类关系表 服务实现类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Service
@Slf4j
public class ArticleClassificationRelationServiceImpl extends ServiceImpl<ArticleClassificationRelationMapper, ArticleClassificationRelationEntity> implements ArticleClassificationRelationService {

}
