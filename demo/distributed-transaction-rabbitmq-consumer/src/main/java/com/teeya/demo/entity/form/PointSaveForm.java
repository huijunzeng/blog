package com.teeya.demo.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel(value = "积分新增表单")
@Data
public class PointSaveForm implements Serializable {

    @ApiModelProperty("账号id")
    @NotBlank(message = "账号id不能为空")
    private Long accountId;

    @ApiModelProperty("账号名称")
    @NotBlank(message = "账号名称不能为空")
    private String accountName;

    @ApiModelProperty("积分")
    @NotBlank(message = "积分不能为空")
    private Integer point;
}
