package com.teeya.user.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: ZJH
 * @Date: 2020/1/7 15:33
 */

@ApiModel(value = "用户角色新增表单")
@Data
public class RoleSaveForm implements Serializable {

    @ApiModelProperty("角色编码")
    @NotBlank(message = "角色编码不能为空")
    @Length(min = 4, max = 8, message = "角色编码长度不合法，请输入4至8位长度之内")
    private String code;

    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    @Length(min = 1, max = 8, message = "角色名称长度不合法，请输入1至8位长度之内")
    private String name;

    @ApiModelProperty("描述")
    private String description;
}
