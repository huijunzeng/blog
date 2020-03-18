package com.teeya.article.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/3/18 18:03
 */

@ApiModel(value = "文章分类vo实体类")
@Data
public class ClassificationForm implements Serializable {

    @ApiModelProperty("分类名称")
    private String classificationName;
}
