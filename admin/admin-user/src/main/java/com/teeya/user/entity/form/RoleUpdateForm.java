package com.teeya.user.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "角色更新表单")
@Data
public class RoleUpdateForm implements Serializable {

    @ApiModelProperty(value = "角色名称", example = "超管")
    @NotBlank(message = "角色名称不能为空")
    @Length(min = 1, max = 8, message = "角色名称长度不合法，请输入1至8位长度之内")
    private String name;

    @ApiModelProperty(value = "描述", example = "超管拥有所有资源权限")
    private String description;
}
