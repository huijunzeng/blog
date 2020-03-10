package com.teeya.article.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.entity.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章标签表
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("label")
public class LabelEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 标签名称
     */
    @TableField("label_name")
    private String labelName;

}
