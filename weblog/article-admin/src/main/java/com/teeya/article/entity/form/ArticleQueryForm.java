package com.teeya.article.entity.form;

import com.teeya.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "文章查询表单")
@Data
public class ArticleQueryForm extends BaseQueryForm {

    @ApiModelProperty(value = "文章标题", example = "docker使用教程")
    private String title;

    @ApiModelProperty(value = "标签id", example = "1")
    private Long labelId;

    @ApiModelProperty(value = "分类id", example = "2")
    private Long classificationId;

    @ApiModelProperty(value = "是否私密不可见 0否 1是", example = "0")
    private String visible;

    @ApiModelProperty(value = "是否已删除 1已删除 0未删除", example = "0")
    private Integer deleted;
}
