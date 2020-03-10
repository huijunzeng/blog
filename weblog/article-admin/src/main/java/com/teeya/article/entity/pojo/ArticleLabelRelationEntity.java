package com.teeya.article.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.entity.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章和标签关系表
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("article_label_relation")
public class ArticleLabelRelationEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 文章id
     */
    @TableField("article_id")
    private String articleId;

    /**
     * 标签id
     */
    @TableField("label_id")
    private String labelId;

}
