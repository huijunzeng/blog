package com.teeya.article.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.web.entity.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

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
    private Long articleId;

    /**
     * 分类id
     */
    @TableField("classification_id")
    private Long classificationId;

}
