package com.teeya.article.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.web.entity.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

/**
 * <p>
 * 文章和标签关系表
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
