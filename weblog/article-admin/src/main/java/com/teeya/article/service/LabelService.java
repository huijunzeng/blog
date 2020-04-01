package com.teeya.article.service;

import com.teeya.article.entity.pojo.LabelEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.article.entity.form.LabelForm;

/**
 * <p>
 * 文章标签表 服务类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
public interface LabelService extends IService<LabelEntity> {

    /**
     * 新增文章标签
     * @param labelForm
     * @return
     */
    int insert(LabelForm labelForm);
}
