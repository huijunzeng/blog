package com.teeya.demo.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel(value = "账号新增表单")
@Data
public class AccountSaveForm implements Serializable {

    @ApiModelProperty("账号名称")
    @NotBlank(message = "账号名称不能为空")
    private String name;

    @ApiModelProperty("手机号码")
    @NotBlank(message = "手机号不能为空")
    private String phone;
}
