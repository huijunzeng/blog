package com.teeya.article.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.web.entity.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

/**
 * <p>
 * 文章标签表
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("label")
public class LabelEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 标签名称
     */
    @TableField("name")
    private String name;

}
