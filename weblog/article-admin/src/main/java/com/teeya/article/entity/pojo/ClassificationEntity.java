package com.teeya.article.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.entity.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章分类表
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("classification")
public class ClassificationEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 分类名称
     */
    @TableField("classification_name")
    private String classificationName;

}
