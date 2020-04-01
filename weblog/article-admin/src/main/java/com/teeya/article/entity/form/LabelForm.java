package com.teeya.article.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "文章标签form表单")
@Data
public class LabelForm implements Serializable {

    @ApiModelProperty("标签名称")
    private String labelName;

}
