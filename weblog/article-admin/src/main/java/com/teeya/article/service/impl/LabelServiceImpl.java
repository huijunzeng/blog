package com.teeya.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teeya.article.entity.form.LabelQueryForm;
import com.teeya.article.entity.form.LabelUpdateForm;
import com.teeya.article.entity.pojo.ArticleLabelRelationEntity;
import com.teeya.article.entity.pojo.LabelEntity;
import com.teeya.article.entity.form.LabelSaveForm;
import com.teeya.article.mapper.LabelMapper;
import com.teeya.article.service.ArticleLabelRelationService;
import com.teeya.article.service.LabelService;
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
 * 文章标签表 服务实现类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Service
@Slf4j
public class LabelServiceImpl extends ServiceImpl<LabelMapper, LabelEntity> implements LabelService {

    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private ArticleLabelRelationService articleLabelRelationService;

    @Override
    public boolean save(LabelSaveForm labelSaveForm) {
        LabelEntity labelEntity = BeanUtils.instantiateClass(LabelEntity.class);
        BeanUtils.copyProperties(labelSaveForm, labelEntity);
        return super.save(labelEntity);
    }

    @Override
    public boolean update(Long id, LabelUpdateForm labelUpdateForm) {
        LabelEntity labelEntity = super.getById(id);
        BeanUtils.copyProperties(labelUpdateForm, labelEntity);
        return super.updateById(labelEntity);
    }

    @Override
    public LabelEntity get(Long id) {
        return super.getById(id);
    }

    @Override
    public List<LabelEntity> queryListByArticleId(Long articleId) {
        QueryWrapper<ArticleLabelRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ArticleLabelRelationEntity::getArticleId, articleId);
        List<ArticleLabelRelationEntity> articleLabelRelationEntities = articleLabelRelationService.list(queryWrapper);
        Set<Long> labelIds = articleLabelRelationEntities.stream().map(articleLabelRelationEntity -> articleLabelRelationEntity.getLabelId()).collect(Collectors.toSet());
        return super.listByIds(labelIds);
    }

    @Override
    public IPage queryList(LabelQueryForm labelQueryForm) {
        Page page = labelQueryForm.getPage();
        LambdaQueryWrapper<LabelEntity> queryWrapper = labelQueryForm.build().lambda();
        queryWrapper.eq(StringUtils.isNotBlank(labelQueryForm.getName()), LabelEntity::getName, labelQueryForm.getName());
        queryWrapper.orderByDesc(LabelEntity::getCreatedTime);
        IPage<LabelEntity> iPageUser = super.page(page, queryWrapper);
        return iPageUser;
    }

    @Override
    public List<LabelEntity> getAll() {
        QueryWrapper<LabelEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(LabelEntity::getDeleted, 0).orderByDesc(LabelEntity::getUpdatedTime);
        return super.list(queryWrapper);
    }

    @Override
    public boolean remove(Long id) {
        return super.removeById(id);
    }
}
