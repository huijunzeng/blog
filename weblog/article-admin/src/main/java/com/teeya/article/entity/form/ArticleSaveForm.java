package com.teeya.article.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "文章form表单")
@Data
public class ArticleSaveForm implements Serializable {

    @ApiModelProperty(value = "文章标题", example = "docker使用教程")
    @NotBlank(message = "文章标题不能为空")
    private String title;

    @ApiModelProperty(value = "文章内容", example = "1231456")
    @NotBlank(message = "文章内容不能为空")
    private String content;

    @ApiModelProperty(value = "标签id集合", example = "1,,2")
    @NotBlank(message = "文章标签不能为空")
    private Set<Long> labelIds;

    @ApiModelProperty(value = "分类id集合", example = "1,,2")
    @NotBlank(message = "文章分类不能为空")
    private Set<Long> classificationIds;

    @ApiModelProperty(value = "是否私密不可见 0否 1是", example = "1")
    private String visible;

    @ApiModelProperty(value = "阅读数", example = "1")
    private Integer readingNumber;

}
