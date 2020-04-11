package com.teeya.user.entity.form;

import com.teeya.common.web.entity.form.BaseQueryForm;
import com.teeya.common.web.entity.form.PageQueryForm;
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

    @ApiModelProperty("资源名称")
    private String name;

    @ApiModelProperty("资源类型")
    private String type;

    @ApiModelProperty("资源url")
    private String url;

}
