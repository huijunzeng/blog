package com.teeya.user.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "资源表单")
@Data
public class ResourceSaveForm implements Serializable {

    @ApiModelProperty("资源名称")
    private String name;

    @ApiModelProperty("资源类型")
    private String type;

    @ApiModelProperty("资源url")
    private String url;
}
