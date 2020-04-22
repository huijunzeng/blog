package com.teeya.article.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/3/18 18:03
 */

@ApiModel(value = "文章分类form表单")
@Data
public class ClassificationSaveForm implements Serializable {

    @ApiModelProperty("分类名称")
    private String name;
}
