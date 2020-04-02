package com.teeya.article.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.entity.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章和分类关系表
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_classification_relation")
public class ArticleClassificationRelationEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 文章id
     */
    @TableField("article_id")
    private String articleId;

    /**
     * 分类id
     */
    @TableField("classification_id")
    private String classificationId;

}
