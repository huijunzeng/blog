package com.teeya.user.entity.form;

import com.teeya.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ZJH
 * @Date: 2020/3/10 15:37
 */

@ApiModel(value = "资源查询表单")
@Data
public class ResourceQueryForm extends BaseQueryForm {

    @ApiModelProperty(value = "资源名称", example = "admin-user")
    private String name;

    @ApiModelProperty(value = "资源类型", example = "资源类型(0菜单 1按钮)")
    private String type;

    @ApiModelProperty(value = "资源url", example = "/admin-user")
    private String url;

}
