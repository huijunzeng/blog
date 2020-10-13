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

@ApiModel(value = "资源新增表单")
@Data
public class ResourceSaveForm implements Serializable {

    @ApiModelProperty(value = "资源名称", example = "admin-user")
    @NotBlank(message = "资源名称不能为空")
    private String name;

    @ApiModelProperty(value = "资源类型", example = "资源类型(0菜单 1按钮)")
    @NotBlank(message = "资源类型不能为空")
    private String type;

    @ApiModelProperty(value = "资源url", example = "/admin-user")
    @NotBlank(message = "资源url不能为空")
    private String url;
}
