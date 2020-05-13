package com.teeya.user.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "资源更新表单")
@Data
public class ResourceUpdateForm implements Serializable {

    @ApiModelProperty("资源名称")
    @NotBlank(message = "资源名称不能为空")
    private String name;

    @ApiModelProperty("资源类型")
    @NotBlank(message = "资源类型不能为空")
    private String type;

    @ApiModelProperty("资源url")
    @NotBlank(message = "资源url不能为空")
    private String url;
}
