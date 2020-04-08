package com.teeya.article.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teeya.common.entity.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

/**
 * <p>
 * 博客文章表
 * </p>
 *
 * @author ZJH
 * @since 2020-03-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("article")
public class ArticleEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 文章标题
     */
    @TableField("title")
    private String title;

    /**
     * 文章内容
     */
    @TableField("content")
    private String content;

    /**
     * 是否私密不可见 0否 1是
     */
    @TableField("visible")
    private String visible;

    /**
     * 阅读数
     */
    @TableField("reading_number")
    private Integer readingNumber;

}
