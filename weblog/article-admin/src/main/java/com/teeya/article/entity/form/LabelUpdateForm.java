package com.teeya.article.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "文章标签更新表单")
@Data
public class LabelUpdateForm implements Serializable {

    @ApiModelProperty("标签名称")
    private String name;
}
