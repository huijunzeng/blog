package com.teeya.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.teeya.article.entity.form.LabelQueryForm;
import com.teeya.article.entity.form.LabelUpdateForm;
import com.teeya.article.entity.pojo.LabelEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.article.entity.form.LabelForm;

import java.util.List;

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
    boolean save(LabelForm labelForm);

    /**
     * 文章标签修改表单
     * @param id
     * @param labelUpdateForm
     * @return
     */
    boolean update(String id, LabelUpdateForm labelUpdateForm);

    /**
     * 根据文章标签id获取指定文章标签信息
     * @param id
     * @return
     */
    LabelEntity get(String id);

    /**
     * 根据文章id获取相应的标签集合
     * @param articleId
     * @return
     */
    List<LabelEntity> queryListByArticleId(String articleId);

    /**
     * 根据条件获取文章标签信息列表
     * @param labelQueryForm
     * @return
     */
    IPage queryList(LabelQueryForm labelQueryForm);

    /**
     * 获取所有文章标签信息列表
     * @return
     */
    List<LabelEntity> getAll();

    /**
     * 根据id删除文章标签
     * @param id
     * @return
     */
    boolean remove(String id);
}
