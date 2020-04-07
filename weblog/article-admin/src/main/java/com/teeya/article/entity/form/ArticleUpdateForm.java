package com.teeya.article.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "文章修改表单")
@Data
public class ArticleUpdateForm implements Serializable {

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章内容")
    private String content;

    @ApiModelProperty("标签id集合")
    private Set<String> labelIds;

    @ApiModelProperty("分类id集合")
    private Set<String> classificationIds;

    @ApiModelProperty("是否私密不可见 0否 1是")
    private String visible;

    @ApiModelProperty("阅读数")
    private Integer readingNumber;

}
