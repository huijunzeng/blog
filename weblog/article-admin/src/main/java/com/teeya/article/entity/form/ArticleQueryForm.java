package com.teeya.article.entity.form;

import com.teeya.common.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "文章查询表单")
@Data
public class ArticleQueryForm extends BaseQueryForm {

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章内容")
    private String content;

    @ApiModelProperty("标签id")
    private String labelId;

    @ApiModelProperty("分类id")
    private String classificationId;

    @ApiModelProperty("是否私密不可见 0否 1是")
    private String visible;

    @ApiModelProperty("阅读数")
    private Integer readingNumber;

}
