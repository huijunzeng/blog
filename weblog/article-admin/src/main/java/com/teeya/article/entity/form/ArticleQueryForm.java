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

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("标签id")
    private Long labelId;

    @ApiModelProperty("分类id")
    private Long classificationId;

    @ApiModelProperty("是否私密不可见 0否 1是")
    private String visible;

    @ApiModelProperty("是否已删除 1已删除 0未删除")
    private Integer deleted;
}
