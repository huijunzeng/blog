package com.teeya.user.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "角色更新表单")
@Data
public class RoleUpdateForm implements Serializable {

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;
}
