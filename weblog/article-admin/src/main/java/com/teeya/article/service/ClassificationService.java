package com.teeya.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.article.entity.form.ClassificationQueryForm;
import com.teeya.article.entity.form.ClassificationUpdateForm;
import com.teeya.article.entity.pojo.ClassificationEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.article.entity.form.ClassificationSaveForm;

import java.util.List;

/**
 * <p>
 * 文章分类表 服务类
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
public interface ClassificationService extends IService<ClassificationEntity> {

    /**
     * 新增文章分类
     * @param classificationSaveForm
     * @return
     */
    boolean save(ClassificationSaveForm classificationSaveForm);

    /**
     * 修改文章分类
     * @param id
     * @param classificationUpdateForm
     * @return
     */
    boolean update(String id, ClassificationUpdateForm classificationUpdateForm);

    /**
     * 根据文章分类id获取指定文章分类信息
     * @param id
     * @return
     */
    ClassificationEntity get(String id);

    /**
     * 根据条件获取文章分类信息列表
     * @param articleId
     * @return
     */
    List<ClassificationEntity> queryListByArticleId(String articleId);

    /**
     * 根据条件获取文章分类信息列表
     * @param classificationQueryForm
     * @return
     */
    IPage queryList(ClassificationQueryForm classificationQueryForm);

    /**
     * 获取所有文章分类信息列表
     * @return
     */
    List<ClassificationEntity> getAll();

    /**
     * 根据id删除文章分类
     * @param id
     * @return
     */
    boolean remove(String id);
}
