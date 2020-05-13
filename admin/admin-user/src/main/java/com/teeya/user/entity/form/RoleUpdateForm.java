package com.teeya.user.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "角色更新表单")
@Data
public class RoleUpdateForm implements Serializable {

    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty("描述")
    private String description;
}
