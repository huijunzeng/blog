package com.teeya.user.entity.form;

import com.teeya.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "角色查询表单")
@Data
public class RoleQueryForm extends BaseQueryForm {

    @ApiModelProperty(value = "角色编码", example = "R001")
    private String code;

    @ApiModelProperty(value = "角色名称", example = "超管")
    private String name;

}
